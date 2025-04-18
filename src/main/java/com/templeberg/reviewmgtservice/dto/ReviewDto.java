package com.templeberg.reviewmgtservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private String id;
    private String title;
    private String description;
    private String author;
    private int stars;

    public ReviewDto(String title, String description, String author, int stars) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.stars = stars;
    }

    public ReviewDto(String id, String title, String description, String author, int stars) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.stars = stars;
    }
}
