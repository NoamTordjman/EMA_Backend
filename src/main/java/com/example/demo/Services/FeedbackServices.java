package com.example.demo.Services;

import com.example.demo.DTO.FeedbackDTOCreate;
import com.example.demo.DTO.FeedbackDTOUpdate;
import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Feedback;
import com.example.demo.User;

import java.util.List;
import java.util.UUID;

public interface FeedbackServices {

    Feedback CreateFeedback(FeedbackDTOCreate feedbackDTO);
    void deleteFeedback(UUID idFeedback);
    Feedback updateFeedback(UUID feedbackID,FeedbackDTOUpdate feedbackDTOUpdate);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById (UUID idFeedback);
    List<Feedback> getFeedbackByUser (UUID userId);
    List<Feedback> getFeedbackByIdCreator(UUID creatorId);
}
