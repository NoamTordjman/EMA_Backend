package com.example.demo.Controller;

import com.example.demo.DTO.FeedbackDTOCreate;
import com.example.demo.DTO.FeedbackDTOUpdate;
import com.example.demo.Feedback;
import com.example.demo.Services.FeedbackServices;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.FeedbackNonExistent;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserNonExistent;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name = "Feedback Controller API",
        description = "Permit to control the Feedback"
)
@RequestMapping("/v1/feedbacks")
public class FeedbackController {

    private final FeedbackServices feedbackService;

    @Autowired
    public FeedbackController(FeedbackServices feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(@RequestBody FeedbackDTOCreate feedbackDTO) throws RegistrationNonExistent {
        Feedback feedback = feedbackService.CreateFeedback(feedbackDTO);
        return ResponseEntity.ok(feedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable UUID id, @RequestBody FeedbackDTOUpdate feedbackDTO) throws FeedbackNonExistent {
        Feedback feedback = feedbackService.updateFeedback(id, feedbackDTO);
        return ResponseEntity.ok(feedback);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable UUID id) throws FeedbackNonExistent {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable UUID id) throws FeedbackNonExistent {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbackByUserId(@PathVariable UUID userId) throws UserNonExistent {
        List<Feedback> feedbacks = feedbackService.getFeedbackByUser(userId);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/getbyeventid/{event_id}")
    public ResponseEntity<List<Feedback>> getFeedbackByEvent(@PathVariable UUID event_id) throws EventNonExistant {
        List<Feedback> feedbacks = feedbackService.getFeedbackByEvent(event_id);
        return ResponseEntity.ok(feedbacks);
    }
}
