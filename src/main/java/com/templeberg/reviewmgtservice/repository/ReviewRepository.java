package com.templeberg.reviewmgtservice.repository;

import com.templeberg.reviewmgtservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {

    Optional<Review> findByAuthor(String author);

    List<Review> findAllByOrderByUpdatedAtDesc();
}
