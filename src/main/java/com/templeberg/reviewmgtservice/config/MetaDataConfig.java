package com.templeberg.reviewmgtservice.config;

import com.templeberg.reviewmgtservice.model.Review;
import com.templeberg.reviewmgtservice.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MetaDataConfig {

    private final ReviewRepository reviewRepository;

    public MetaDataConfig(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @PostConstruct
    public void init() {
        addReviewMetaData();
    }

    private void addReviewMetaData() {
        if (reviewRepository.count() > 0) return;

        List<Review> reviews = List.of(
                new Review("Breathtaking Views", "The sunrise from the balcony was unforgettable.", "Naduni", 5),
                new Review("Relaxing Retreat", "Perfect getaway from the city. Peaceful and quiet.", "Tharindu", 4),
                new Review("Very Clean & Cozy", "Spotless rooms and cozy interiors. Highly recommended!", "Ishara", 5),
                new Review("Delicious Food", "The meals were authentic and super tasty!", "Kasun", 4),
                new Review("Warm Hospitality", "Felt like home. Staff were always smiling.", "Amaya", 5),
                new Review("Nice But Noisy", "Great place but a bit noisy during the day.", "Supun", 3),
                new Review("Perfect for Families", "Kids loved the pool and garden area!", "Janani", 4),
                new Review("Fast Service", "Quick check-in and prompt room service.", "Ravindu", 5),
                new Review("Average Stay", "Was okay but expected more for the price.", "Chamari", 3),
                new Review("Great Location", "Close to attractions. Easy to get around.", "Dineth", 4),
                new Review("Internet Issues", "Wi-Fi was unreliable. Needs improvement.", "Harsha", 2),
                new Review("Romantic Atmosphere", "Ideal for couples. Beautiful setting.", "Sanduni", 5),
                new Review("Fantastic Pool", "The pool area was clean and relaxing.", "Nipun", 4),
                new Review("Friendly Staff", "They really go the extra mile!", "Mevan", 5),
                new Review("Could Improve Rooms", "Rooms need a refresh. A bit outdated.", "Dilsha", 3)
        );

        reviewRepository.saveAll(reviews);
    }
}
