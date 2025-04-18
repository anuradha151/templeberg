package com.templeberg.reviewmgtservice.dto;

import com.templeberg.reviewmgtservice.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {
    private String id;
    private String title;
    private String description;
    private String author;
    private int stars;
    private CommonStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewDto() {
    }

    public ReviewDto(String title, String description, String author, int stars, CommonStatus status) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.stars = stars;
        this.status = status;
    }

    public ReviewDto(String id, String title, String description, String author, int stars, CommonStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.stars = stars;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
