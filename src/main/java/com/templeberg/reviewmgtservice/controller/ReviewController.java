package com.templeberg.reviewmgtservice.controller;

import com.templeberg.reviewmgtservice.dto.ReviewDto;
import com.templeberg.reviewmgtservice.enums.CommonStatus;
import com.templeberg.reviewmgtservice.service.ApplicationStateService;
import com.templeberg.reviewmgtservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ApplicationStateService applicationStateService;

    @GetMapping("/")
    public String index(Model model) {
        if (applicationStateService.getLoggedUser() == null) {
            return "redirect:/login";
        }
        model.addAttribute("reviews", reviewService.findAll());
        return "index";
    }

    @GetMapping("/add-review")
    public String showAddReviewPage() {
        return "add-review";
    }

    @PostMapping("/add-review")
    public String submitReview(@RequestParam String title,
                               @RequestParam String description,
                               @RequestParam String author,
                               @RequestParam int stars) {

        ReviewDto dto = new ReviewDto(title, description, author, stars, CommonStatus.PREVIEW);
        reviewService.save(dto);

        return "redirect:/";
    }
}
