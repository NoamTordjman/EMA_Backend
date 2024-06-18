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
        Registration registration = RegistrationRepository.findById(feedbackDTO.getRegistrationId()).orElse(null);
        Feedback feedback = new Feedback();
        feedback.setRegistration(registration);
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRating(feedbackDTO.getRating());
        return FeedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(UUID feedbackID, FeedbackDTOUpdate feedbackDTOUpdate) {
        Feedback feedback = FeedbackRepository.findById(feedbackID).orElse(null);
        assert feedback != null;
        feedback.setRating(feedbackDTOUpdate.getRating());
        feedback.setDescription(feedbackDTOUpdate.getComments());
        Registration registration = RegistrationRepository.findById(feedback.getRegistration().getRegistrationID()).orElse(null);
        feedback.setRegistration(registration);
        return FeedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(UUID idFeedback) {
        FeedbackRepository.deleteById(idFeedback);
    }


    @Override
    public List<Feedback> getAllFeedback() {
        return FeedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(UUID idFeedback) {
        return FeedbackRepository.findById(idFeedback).orElse(null);
    }

    @Override
    public List<Feedback> getFeedbackByUser(UUID UserID) {
        User user = UserRepository.findById(UserID).orElse(null);
        if (user == null) {
            return Collections.emptyList();
        }
        List<Registration> registrations = RegistrationRepository.findByUser(user);
        return FeedbackRepository.findByRegistrationIn(registrations);
    }

    @Override
    public List<Feedback> getFeedbackByIdCreator(UUID idCreator) {
        User user = UserRepository.findById(idCreator).orElse(null);
        List<Event> events = EventRepository.findByIdCreator(user);
        List<Registration> registrations = RegistrationRepository.findByEventIn(events);
        return FeedbackRepository.findByRegistrationIn(registrations);
    }


}
