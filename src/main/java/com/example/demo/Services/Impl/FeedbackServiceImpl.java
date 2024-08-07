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

/**
 * Service implémentant la gestion des retours d'expérience (feedbacks).
 * Ce service offre des fonctionnalités pour créer, mettre à jour, supprimer et récupérer des feedbacks.
 */
@Service
public class FeedbackServiceImpl implements FeedbackServices {

    private final FeedbackRepository feedbackRepository;
    private final RegistrationServices registrationService;
    private final UserServices userService;
    private final EventServices eventService;

    /**
     * Constructeur pour l'injection de dépendances.
     *
     * @param feedbackRepository Repository gérant les données des feedbacks.
     * @param registrationService Service gérant les inscriptions.
     * @param userService Service gérant les utilisateurs.
     * @param eventService Service gérant les événements.
     */
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, RegistrationServices registrationService, UserServices userService, EventServices eventService) {
        this.feedbackRepository = feedbackRepository;
        this.registrationService = registrationService;
        this.userService = userService;
        this.eventService = eventService;
    }

    /**
     * Crée un feedback basé sur les données fournies dans le DTO.
     *
     * @param feedbackDTO DTO contenant les informations du feedback à créer.
     * @return Le feedback créé.
     * @throws RegistrationNonExistent Exception si l'inscription n'existe pas.
     */
    @Override
    public Feedback CreateFeedback(FeedbackDTOCreate feedbackDTO) throws RegistrationNonExistent {
        Registration registration = registrationService.getRegistrationById(feedbackDTO.getRegistrationId());
        Feedback feedback = new Feedback();
        feedback.setRegistration(registration);
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRating(feedbackDTO.getRating());
        return feedbackRepository.save(feedback);
    }

    /**
     * Met à jour un feedback existant.
     *
     * @param feedbackID ID du feedback à mettre à jour.
     * @param feedbackDTOUpdate DTO contenant les nouvelles informations.
     * @return Le feedback mis à jour.
     * @throws FeedbackNonExistent Exception si le feedback n'existe pas.
     */
    @Override
    public Feedback updateFeedback(UUID feedbackID, FeedbackDTOUpdate feedbackDTOUpdate) throws FeedbackNonExistent {
        Feedback feedback = feedbackRepository.findById(feedbackID).orElseThrow(() -> new FeedbackNonExistent(feedbackID));
        feedback.setRating(feedbackDTOUpdate.getRating());
        feedback.setDescription(feedbackDTOUpdate.getComments());
        Registration registration = feedback.getRegistration();
        feedback.setRegistration(registration);
        return feedbackRepository.save(feedback);
    }

    /**
     * Supprime un feedback spécifié par son ID.
     *
     * @param idFeedback ID du feedback à supprimer.
     * @throws FeedbackNonExistent Exception si le feedback n'existe pas.
     */
    @Override
    public void deleteFeedback(UUID idFeedback) throws FeedbackNonExistent {
        Feedback feedback = feedbackRepository.getReferenceById(idFeedback);
        feedbackRepository.deleteById(idFeedback);
    }

    /**
     * Récupère tous les feedbacks disponibles.
     *
     * @return Liste de tous les feedbacks.
     */
    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    /**
     * Récupère un feedback par son ID.
     *
     * @param idFeedback ID du feedback à récupérer.
     * @return Le feedback correspondant.
     * @throws FeedbackNonExistent Exception si le feedback n'existe pas.
     */
    @Override
    public Feedback getFeedbackById(UUID idFeedback) throws FeedbackNonExistent {
        return feedbackRepository.findById(idFeedback).orElseThrow(() -> new FeedbackNonExistent(idFeedback));
    }

    /**
     * Récupère les feedbacks associés à un utilisateur spécifique.
     *
     * @param userID ID de l'utilisateur.
     * @return Liste des feedbacks de l'utilisateur.
     * @throws UserNonExistent Exception si l'utilisateur n'existe pas.
     */
    @Override
    public List<Feedback> getFeedbackByUser(UUID userID) throws UserNonExistent {
        User user = userService.getUserById(userID);
        List<Registration> registrations = registrationService.getRegistrationByUserId(user.getId());
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(userID);
        }
        return feedbackRepository.findByRegistrationIn(registrations);
    }

    /**
     * Récupère les feedbacks associés à un événement spécifique.
     *
     * @param eventId ID de l'événement.
     * @return Liste des feedbacks de l'événement.
     * @throws EventNonExistant Exception si l'événement n'existe pas.
     */
    @Override
    public List<Feedback> getFeedbackByEvent(UUID eventId) throws EventNonExistant {
        Event event = eventService.getEventById(eventId);
        System.out.println(event);
        return feedbackRepository.findByEvent(event);
    }
}
