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

    @PostMapping("/create")
    public Feedback createFeedback(@RequestBody FeedbackDTOCreate feedbackDTO) throws RegistrationNonExistent {
        return feedbackService.CreateFeedback(feedbackDTO);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable UUID id, @RequestBody FeedbackDTOUpdate feedbackDTO) throws FeedbackNonExistent {
        return feedbackService.updateFeedback(id, feedbackDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFeedback(@PathVariable UUID id) throws FeedbackNonExistent {
        feedbackService.deleteFeedback(id);
    }

    @GetMapping("/getall")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable UUID id)throws FeedbackNonExistent {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping("/userid/{userId}")
    public List<Feedback> getFeedbackByUserId(@PathVariable UUID userId) throws UserNonExistent {
        return feedbackService.getFeedbackByUser(userId);
    }

    @GetMapping("/getbyeventid/{event_id}")
    public ResponseEntity<List<Feedback>> getFeedbackbyevent(@PathVariable UUID event_id) throws EventNonExistant{
        List<Feedback> list=feedbackService.getFeedbackByEvent(event_id);
        return ResponseEntity.ok(list);
    }

}
