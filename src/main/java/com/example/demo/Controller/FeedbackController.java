package com.example.demo.Controller;

import com.example.demo.DTO.FeedbackDTOCreate;
import com.example.demo.DTO.FeedbackDTOUpdate;
import com.example.demo.Feedback;
import com.example.demo.Services.FeedbackServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

@Tag(
        name="Feedback Controller API",
        description = "Permit to control the Feedback"
)
@RequestMapping("/v1/feedbacks")
public class FeedbackController {

    private final FeedbackServices feedbackService;

    @Autowired
    public FeedbackController(FeedbackServices feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody FeedbackDTOCreate feedbackDTO) {
        return feedbackService.CreateFeedback(feedbackDTO);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable UUID id, @RequestBody FeedbackDTOUpdate feedbackDTO) {
        return feedbackService.updateFeedback(id, feedbackDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable UUID id) {
        feedbackService.deleteFeedback(id);
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable UUID id) {
        return feedbackService.getFeedbackById(id);
    }
}
