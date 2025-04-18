package com.templeberg.reviewmgtservice.service;

import com.templeberg.reviewmgtservice.dto.ReviewDto;
import com.templeberg.reviewmgtservice.model.Review;
import com.templeberg.reviewmgtservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

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
        return reviewRepository.findAllByOrderByUpdatedAtDesc()
                .stream()
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
                review.getStatus()
        );
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
