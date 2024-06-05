package com.example.demo.Services.Impl;

import com.example.demo.DTO.FeedbackDTOCreate;
import com.example.demo.DTO.FeedbackDTOUpdate;
import com.example.demo.Feedback;
import com.example.demo.Registration;
import com.example.demo.Repository.FeedbackRepository;
import com.example.demo.Repository.RegistrationRepository;
import com.example.demo.Services.FeedbackServices;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackServices {

    FeedbackRepository FeedbackRepository;
    RegistrationRepository RegistrationRepository;

    public FeedbackServiceImpl(com.example.demo.Repository.FeedbackRepository feedbackRepository, com.example.demo.Repository.RegistrationRepository registrationRepository) {
        FeedbackRepository = feedbackRepository;
        RegistrationRepository = registrationRepository;
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
}
