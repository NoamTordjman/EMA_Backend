package com.example.demo.Services.Impl;

import com.example.demo.DTO.FeedbackDTOCreate;
import com.example.demo.DTO.FeedbackDTOUpdate;
import com.example.demo.Event;
import com.example.demo.Feedback;
import com.example.demo.Registration;
import com.example.demo.Repository.FeedbackRepository;
import com.example.demo.Services.EventServices;
import com.example.demo.Services.FeedbackServices;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import com.example.demo.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackServices {

    private final FeedbackRepository FeedbackRepository;
    private final RegistrationServices RegistrationService;
    private final UserServices UserService;
    private final EventServices EventService;


    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, RegistrationServices registrationService, UserServices userService, EventServices eventService) {
        FeedbackRepository = feedbackRepository;
        RegistrationService = registrationService;
        UserService = userService;
        EventService = eventService;
    }

    @Override
    public Feedback CreateFeedback(FeedbackDTOCreate feedbackDTO) throws RegistrationNonExistent{
        Registration registration = RegistrationService.getRegistrationById(feedbackDTO.getRegistrationId());
        Feedback feedback = new Feedback();
        feedback.setRegistration(registration);
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRating(feedbackDTO.getRating());
        return FeedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(UUID feedbackID, FeedbackDTOUpdate feedbackDTOUpdate) throws FeedbackNonExistent{
        Feedback feedback = FeedbackRepository.findById(feedbackID).orElseThrow(()-> new FeedbackNonExistent(feedbackID));
        feedback.setRating(feedbackDTOUpdate.getRating());
        feedback.setDescription(feedbackDTOUpdate.getComments());
        Registration registration = feedback.getRegistration();
        feedback.setRegistration(registration);
        return FeedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(UUID idFeedback) throws FeedbackNonExistent{
        Feedback feedback = FeedbackRepository.getReferenceById(idFeedback);
        FeedbackRepository.deleteById(idFeedback);
    }


    @Override
    public List<Feedback> getAllFeedback() {
        return FeedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(UUID idFeedback) throws FeedbackNonExistent{
        return FeedbackRepository.findById(idFeedback).orElseThrow(()-> new FeedbackNonExistent(idFeedback));
    }

    @Override
    public List<Feedback> getFeedbackByUser(UUID UserID) throws UserNonExistent {
        User user = UserService.getUserById(UserID);
        List<Registration> registrations = RegistrationService.getRegistrationByUserId(user.getId());
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(UserID);
        }
        return FeedbackRepository.findByRegistrationIn(registrations);
    }

    @Override
    public List<Feedback> getFeedbackByEvent(UUID eventId) throws EventNonExistant {
        Event event=EventService.getEventById(eventId);
        System.out.println(event);
        return FeedbackRepository.findByEvent(event);
    }


}