package com.example.demo.Services.Impl;

import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Event;
import com.example.demo.Registration;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.RegistrationRepository;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserAlreadyRegisteredException;
import com.example.demo.exception.UserNonExistent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service implémentant la gestion des inscriptions aux événements.
 * Ce service permet de gérer les opérations CRUD pour les inscriptions d'utilisateurs aux événements.
 */
@Service
public class RegistrationServiceImpl implements RegistrationServices {

    private final RegistrationRepository registrationRepository;
    private final UserServices userService;
    private final EventRepository eventRepository;

    /**
     * Constructeur pour l'injection des dépendances nécessaires.
     *
     * @param registrationRepository Référence au repository de gestion des inscriptions.
     * @param userService Service de gestion des utilisateurs.
     * @param eventRepository Repository pour accéder aux données des événements.
     */
    public RegistrationServiceImpl(RegistrationRepository registrationRepository, UserServices userService, EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    /**
     * Récupère toutes les inscriptions pour un utilisateur donné.
     *
     * @param id_user ID de l'utilisateur.
     * @return Liste des inscriptions de l'utilisateur.
     * @throws UserNonExistent Si l'utilisateur n'existe pas.
     */
    @Override
    public List<Registration> getRegistrationByUserId(UUID id_user) throws UserNonExistent {
        User u = userService.getUserById(id_user);
        return registrationRepository.findByUser(u);
    }

    /**
     * Crée une nouvelle inscription à partir des données fournies.
     *
     * @param registrationDTO DTO contenant les informations de l'inscription.
     * @return L'inscription créée.
     * @throws UserAlreadyRegisteredException Si l'utilisateur est déjà inscrit à l'événement.
     * @throws UserNonExistent Si l'utilisateur n'existe pas.
     * @throws EventNonExistant Si l'événement n'existe pas.
     */
    @Override
    public Registration CreateRegistration(RegistrationDTOCreate registrationDTO) throws UserAlreadyRegisteredException, UserNonExistent, EventNonExistant {
        User user = userService.getUserById(registrationDTO.getUserId());
        Event event = eventRepository.findById(registrationDTO.getEventId()).orElseThrow(() -> new EventNonExistant(registrationDTO.getUserId()));
        if (registrationRepository.existsByUserAndEvent(user, event)) {
            throw new UserAlreadyRegisteredException(registrationDTO.getUserId());
        }
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);
        return registrationRepository.save(registration);
    }

    /**
     * Supprime une inscription spécifiée par son ID.
     *
     * @param id_Registration ID de l'inscription à supprimer.
     * @throws EventNonExistant Si l'événement lié à l'inscription n'existe pas.
     */
    @Override
    public void deleteRegistration(UUID id_Registration) throws EventNonExistant {
        getRegistrationById(id_Registration); // Assure que l'inscription existe avant de supprimer
        registrationRepository.deleteById(id_Registration);
    }

    /**
     * Récupère toutes les inscriptions.
     *
     * @return Liste de toutes les inscriptions.
     */
    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    /**
     * Récupère une inscription par son ID.
     *
     * @param id_Registration ID de l'inscription à récupérer.
     * @return L'inscription correspondante.
     * @throws EventNonExistant Si l'inscription n'existe pas.
     */
    @Override
    public Registration getRegistrationById(UUID id_Registration) throws EventNonExistant {
        return registrationRepository.findById(id_Registration).orElseThrow(() -> new RegistrationNonExistent(id_Registration));
    }
}
