package com.templeberg.reviewmgtservice.config;

import com.templeberg.reviewmgtservice.enums.CommonStatus;
import com.templeberg.reviewmgtservice.enums.UserRole;
import com.templeberg.reviewmgtservice.model.Review;
import com.templeberg.reviewmgtservice.model.User;
import com.templeberg.reviewmgtservice.repository.ReviewRepository;
import com.templeberg.reviewmgtservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class MetaDataConfig {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        addUsers();
        addReviewMetaData();
    }

    private void addUsers() {
        if (userRepository.count() > 0) return;

        List<User> users = List.of(
                new User("naveen01", "naveen01@example.com", passwordEncoder.encode("userpass1"), UserRole.USER),
                new User("sachini02", "sachini02@example.com", passwordEncoder.encode("userpass2"), UserRole.USER),
                new User("manula03", "manula03@example.com", passwordEncoder.encode("userpass3"), UserRole.USER),
                new User("iraj04", "iraj04@example.com", passwordEncoder.encode("userpass4"), UserRole.USER),
                new User("dinali05", "dinali05@example.com", passwordEncoder.encode("userpass5"), UserRole.USER),

                new User("admin_suresh", "suresh.admin@example.com", passwordEncoder.encode("adminpass1"), UserRole.ADMIN),
                new User("admin_kavya", "kavya.admin@example.com", passwordEncoder.encode("adminpass2"), UserRole.ADMIN),
                new User("admin_mahesh", "mahesh.admin@example.com", passwordEncoder.encode("adminpass3"), UserRole.ADMIN),
                new User("admin_nirmala", "nirmala.admin@example.com", passwordEncoder.encode("adminpass4"), UserRole.ADMIN),
                new User("admin_bimal", "bimal.admin@example.com", passwordEncoder.encode("adminpass5"), UserRole.ADMIN)
        );

        userRepository.saveAll(users);
    }


    private void addReviewMetaData() {
        if (reviewRepository.count() > 0) return;

        List<Review> reviews = List.of(
                new Review("Breathtaking Views", "The sunrise from the balcony was unforgettable.", "Naduni", 5, CommonStatus.DRAFT),
                new Review("Relaxing Retreat", "Perfect getaway from the city. Peaceful and quiet.", "Tharindu", 4, CommonStatus.DRAFT),
                new Review("Very Clean & Cozy", "Spotless rooms and cozy interiors. Highly recommended!", "Ishara", 5, CommonStatus.DRAFT),
                new Review("Delicious Food", "The meals were authentic and super tasty!", "Kasun", 4, CommonStatus.DRAFT),
                new Review("Warm Hospitality", "Felt like home. Staff were always smiling.", "Amaya", 5, CommonStatus.PUBLISHED),
                new Review("Nice But Noisy", "Great place but a bit noisy during the day.", "Supun", 3, CommonStatus.PUBLISHED),
                new Review("Perfect for Families", "Kids loved the pool and garden area!", "Janani", 4, CommonStatus.PUBLISHED),
                new Review("Fast Service", "Quick check-in and prompt room service.", "Ravindu", 5, CommonStatus.PUBLISHED),
                new Review("Average Stay", "Was okay but expected more for the price.", "Chamari", 3, CommonStatus.PUBLISHED),
                new Review("Great Location", "Close to attractions. Easy to get around.", "Dineth", 4, CommonStatus.DRAFT),
                new Review("Internet Issues", "Wi-Fi was unreliable. Needs improvement.", "Harsha", 2, CommonStatus.DRAFT),
                new Review("Romantic Atmosphere", "Ideal for couples. Beautiful setting.", "Sanduni", 5, CommonStatus.PUBLISHED),
                new Review("Fantastic Pool", "The pool area was clean and relaxing.", "Nipun", 4, CommonStatus.PUBLISHED),
                new Review("Friendly Staff", "They really go the extra mile!", "Mevan", 5, CommonStatus.DRAFT),
                new Review("Could Improve Rooms", "Rooms need a refresh. A bit outdated.", "Dilsha", 3,  CommonStatus.DRAFT)
        );

        reviewRepository.saveAll(reviews);
    }
}
