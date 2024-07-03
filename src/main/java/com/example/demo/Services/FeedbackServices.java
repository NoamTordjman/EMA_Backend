package com.example.demo.Services;

import com.example.demo.DTO.FeedbackDTOCreate;
import com.example.demo.DTO.FeedbackDTOUpdate;
import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Feedback;
import com.example.demo.User;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.FeedbackNonExistent;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserNonExistent;

import java.util.List;
import java.util.UUID;

public interface FeedbackServices {

    Feedback CreateFeedback(FeedbackDTOCreate feedbackDTO) throws  RegistrationNonExistent;
    void deleteFeedback(UUID idFeedback) throws FeedbackNonExistent;
    Feedback updateFeedback(UUID feedbackID,FeedbackDTOUpdate feedbackDTOUpdate) throws FeedbackNonExistent;
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById (UUID idFeedback) throws FeedbackNonExistent;
    List<Feedback> getFeedbackByUser (UUID userId) throws UserNonExistent;
    List<Feedback> getFeedbackByEvent (UUID eventId) throws EventNonExistant;
}
