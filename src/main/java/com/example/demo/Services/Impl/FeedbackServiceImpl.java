package com.example.demo.Services.Impl;

import com.example.demo.DTO.FeedbackDTOCreate;
import com.example.demo.DTO.FeedbackDTOUpdate;
import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.Event;
import com.example.demo.Feedback;
import com.example.demo.Registration;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.FeedbackRepository;
import com.example.demo.Repository.RegistrationRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.FeedbackServices;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.User;
import com.example.demo.exception.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackServices {

    private final FeedbackRepository FeedbackRepository;
    private final RegistrationRepository RegistrationRepository;
    private final UserRepository UserRepository;
    private final EventRepository EventRepository;

    public FeedbackServiceImpl(com.example.demo.Repository.FeedbackRepository feedbackRepository, com.example.demo.Repository.RegistrationRepository registrationRepository, com.example.demo.Repository.UserRepository userRepository, com.example.demo.Repository.EventRepository eventRepository) {
        FeedbackRepository = feedbackRepository;
        RegistrationRepository = registrationRepository;
        UserRepository = userRepository;
        EventRepository = eventRepository;
    }

    @Override
    public Feedback CreateFeedback(FeedbackDTOCreate feedbackDTO) {
        Registration registration = RegistrationRepository.findById(feedbackDTO.getRegistrationId()).orElseThrow(()-> new FeedbackNonExistent("Registration not found"));
        Feedback feedback = new Feedback();
        feedback.setRegistration(registration);
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRating(feedbackDTO.getRating());
        return FeedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(UUID feedbackID, FeedbackDTOUpdate feedbackDTOUpdate) {
        Feedback feedback = FeedbackRepository.findById(feedbackID).orElseThrow(()-> new FeedbackNonExistent("Feedback not found"));
        feedback.setRating(feedbackDTOUpdate.getRating());
        feedback.setDescription(feedbackDTOUpdate.getComments());
        Registration registration = RegistrationRepository.findById(feedback.getRegistration().getRegistrationID()).orElse(null);
        feedback.setRegistration(registration);
        return FeedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(UUID idFeedback) {
        Feedback feedback = FeedbackRepository.findById(idFeedback).orElseThrow(()-> new FeedbackNonExistent("Feedback not found"));
        FeedbackRepository.deleteById(idFeedback);
    }


    @Override
    public List<Feedback> getAllFeedback() {
        return FeedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(UUID idFeedback) {
        return FeedbackRepository.findById(idFeedback).orElseThrow(()-> new FeedbackNonExistent("Feedback not found"));
    }

    @Override
    public List<Feedback> getFeedbackByUser(UUID UserID) {
        User user = UserRepository.findById(UserID).orElseThrow(()-> new UserNonExistent(UserID));
        List<Registration> registrations = RegistrationRepository.findByUser(user);
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent("Registration not found");
        }
        return FeedbackRepository.findByRegistrationIn(registrations);
    }

    @Override
    public List<Feedback> getFeedbackByIdCreator(UUID idCreator) {
        User user = UserRepository.findById(idCreator).orElseThrow(()-> new UserNonExistent(idCreator));
        List<Event> events = EventRepository.findByIdCreator(user);
        if (events.isEmpty()) {
            throw new EventNonExistant("Event not found");
        }
        List<Registration> registrations = RegistrationRepository.findByEventIn(events);
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent("Registration not found");
        }
        return FeedbackRepository.findByRegistrationIn(registrations);
    }


}
