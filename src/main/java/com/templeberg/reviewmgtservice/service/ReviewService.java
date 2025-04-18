package com.templeberg.reviewmgtservice.service;

import com.templeberg.reviewmgtservice.dto.ReviewDto;
import com.templeberg.reviewmgtservice.enums.CommonStatus;
import com.templeberg.reviewmgtservice.enums.UserRole;
import com.templeberg.reviewmgtservice.model.Review;
import com.templeberg.reviewmgtservice.model.User;
import com.templeberg.reviewmgtservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ApplicationStateService applicationStateService;

    public void save(ReviewDto dto) {
        validateSaveRequest(dto);
        reviewRepository.findByAuthor(dto.getAuthor())
                .ifPresentOrElse(review -> {
                            review.setTitle(dto.getTitle());
                            review.setDescription(dto.getDescription());
                            review.setStars(dto.getStars());
                            reviewRepository.save(review);
                        }, () -> {
                            reviewRepository.save(
                                    new Review(
                                            dto.getTitle(),
                                            dto.getDescription(),
                                            dto.getAuthor(),
                                            dto.getStars(),
                                            dto.getStatus()
                                    )
                            );
                        }
                );


    }

    public List<ReviewDto> findAll() {
        User loggedUser = applicationStateService.getLoggedUser();

        List<Review> reviews;

        if (loggedUser != null && loggedUser.getRole() == UserRole.USER) {
            reviews = reviewRepository.findAllByStatusOrderByUpdatedAtDesc(CommonStatus.PUBLISHED);
        } else {
            reviews = reviewRepository.findAllByOrderByUpdatedAtDesc();
        }

        return reviews.stream()
                .map(this::toReviewDto)
                .toList();
    }

        public ReviewDto toReviewDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getTitle(),
                review.getDescription(),
                review.getAuthor(),
                review.getStars(),
                review.getStatus(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }

    public ReviewDto findById(String id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        return toReviewDto(review);
    }

    public void update(ReviewDto dto) {
        validateUpdateRequest(dto);

        Review review = reviewRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        review.setTitle(dto.getTitle());
        review.setDescription(dto.getDescription());
        review.setStars(dto.getStars());
        review.setStatus(dto.getStatus());
        review.setUpdatedAt(LocalDateTime.now());

        reviewRepository.save(review);
    }

    private void validateUpdateRequest(ReviewDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isEmpty())
            throw new IllegalArgumentException("Title cannot be empty");

        if (dto.getDescription() == null || dto.getDescription().isEmpty())
            throw new IllegalArgumentException("Description cannot be empty");
    }

    private void validateSaveRequest(ReviewDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isEmpty())
            throw new IllegalArgumentException("Title cannot be empty");

        if (dto.getDescription() == null || dto.getDescription().isEmpty())
            throw new IllegalArgumentException("Description cannot be empty");

        if (dto.getAuthor() == null || dto.getAuthor().isEmpty())
            throw new IllegalArgumentException("Author cannot be empty");

    }

}
