package com.templeberg.reviewmgtservice.model;

import com.templeberg.reviewmgtservice.enums.CommonStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @UuidGenerator
    private String id;
    private String title;
    private String description;
    private String author;
    private int stars;
    @Enumerated(EnumType.STRING)
    private CommonStatus status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;


    public Review() {}

    public Review(String title, String description, String author, int stars, CommonStatus status) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.stars = stars;
        this.status = status;
    }

}
