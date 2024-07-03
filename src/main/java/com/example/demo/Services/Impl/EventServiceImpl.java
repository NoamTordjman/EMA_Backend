package com.example.demo.Services.Impl;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.EventFilterDTO;
import com.example.demo.Event;
import com.example.demo.EventSpecification;
import com.example.demo.Registration;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Services.EventServices;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import com.example.demo.exception.EventCannotBeDeletedException;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserNonExistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

/**
 * Service pour la gestion des événements.
 * Cette classe implémente les méthodes nécessaires pour créer, mettre à jour, supprimer, et rechercher des événements.
 */
@Service
public class EventServiceImpl implements EventServices {

    @Autowired
    private final EventRepository repository;
    private final RegistrationServices registrationService;
    private final UserServices userService;

    /**
     * Constructeur pour l'injection de dépendances.
     *
     * @param repository Référence au repository gérant les données des événements.
     * @param registrationService Service de gestion des inscriptions.
     * @param userService Service de gestion des utilisateurs.
     */
    @Autowired
    public EventServiceImpl(EventRepository repository, RegistrationServices registrationService, UserServices userService) {
        this.repository = repository;
        this.registrationService = registrationService;
        this.userService = userService;
    }

    /**
     * Crée un nouvel événement à partir d'un DTO.
     *
     * @param EventDTO Les détails de l'événement à créer.
     * @return L'événement créé.
     * @throws UserNonExistent Exception si l'utilisateur créateur n'existe pas.
     */
    @Override
    public Event CreateEvent(EventDTOCreate EventDTO) throws UserNonExistent{
        Event event = new Event();
        event.setTitle(EventDTO.getTitle());
        event.setDescription(EventDTO.getDescription());
        event.setEventStatus(EventDTO.getEventStatus());
        event.setDateBegining(EventDTO.getDateBeginning());
        event.setDate_end(EventDTO.getDate_end());
        User creator = userService.getUserById(EventDTO.getIdCreator());
        event.setCreator(creator);
        event.setLocation(EventDTO.getLocation());
        return repository.save(event);
    }

    /**
     * Met à jour un événement existant avec les informations fournies dans un DTO.
     *
     * @param EventID L'ID de l'événement à mettre à jour.
     * @param EventDTO Le DTO contenant les informations de mise à jour.
     * @return L'événement mis à jour.
     * @throws EventNonExistant Exception si l'événement n'existe pas.
     * @throws UserNonExistent Exception si l'utilisateur créateur n'existe pas.
     */
    @Override
    public Event updateEvent(UUID EventID, EventDTOUpdate EventDTO) throws EventNonExistant {
        Event event = getEventById(EventID);
        event.setTitle(EventDTO.getTitle());
        event.setDescription(EventDTO.getDescription());
        event.setEventStatus(EventDTO.getEventStatus());
        event.setDateBegining(EventDTO.getDateBegining());
        event.setDate_end(EventDTO.getDate_end());
        event.setLocation(EventDTO.getLocation());
        return repository.save(event);
    }

    /**
     * Supprime un événement spécifié par son ID.
     *
     * @param idEvent L'ID de l'événement à supprimer.
     * @throws EventNonExistant Exception si l'événement n'existe pas.
     * @throws EventCannotBeDeletedException Exception si l'événement ne peut pas être supprimé car il a des inscriptions actives.
     */
    @Override
    public void deleteEvent(UUID idEvent) throws EventNonExistant {
        Event event = getEventById(idEvent);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date_end = event.getDate_end();

        if(date_end.isBefore(now)){
            throw new EventCannotBeDeletedException();
        }

        repository.deleteById(idEvent);
    }

    /**
     * Récupère la liste de tous les événements.
     *
     * @return Liste de tous les événements disponibles.
     */
    @Override
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    /**
     * Récupère les événements créés par un utilisateur spécifique.
     *
     * @param Id_Creator ID de l'utilisateur créateur des événements.
     * @return Liste des événements créés par l'utilisateur.
     * @throws UserNonExistent Exception si l'utilisateur n'existe pas.
     */
    @Override
    public List<Event> getEventByIdCreator(UUID Id_Creator) throws UserNonExistent {
        User user = userService.getUserById(Id_Creator);
        return repository.findByIdCreator(user);
    }

    /**
     * Récupère un événement par son ID.
     *
     * @param idEvent L'ID de l'événement à récupérer.
     * @return L'événement correspondant.
     * @throws EventNonExistant Exception si l'événement n'existe pas.
     */
    @Override
    public Event getEventById(UUID idEvent) throws EventNonExistant {
        Event event = repository.findById(idEvent).orElseThrow(()-> new EventNonExistant(idEvent));
        return repository.findById(idEvent).orElse(null);
    }

    /**
     * Recherche des événements selon les critères spécifiés dans un DTO de filtrage.
     *
     * @param eventFilterDTO Le DTO contenant les critères de filtrage.
     * @return Liste des événements correspondant aux critères.
     */
    @Override
    public List<Event> searchEvents(EventFilterDTO eventFilterDTO) {
        Specification<Event> specification = EventSpecification.withCriteria(eventFilterDTO);
        return repository.findAll(specification);
    }

    /**
     * Récupère les événements auxquels un membre est inscrit.
     *
     * @param memberId ID du membre.
     * @return Ensemble des événements auxquels le membre est inscrit.
     * @throws UserNonExistent Exception si l'utilisateur n'existe pas.
     * @throws RegistrationNonExistent Exception si l'inscription n'existe pas.
     */
    @Override
    public Set<Event> getEventByMember(UUID memberId) throws UserNonExistent, RegistrationNonExistent {
        User user = userService.getUserById(memberId);
        List<Registration> registrations = registrationService.getRegistrationByUserId(user.getId());
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(memberId);
        }
        List<Event> events = new ArrayList<>();

        for (Registration registration : registrations) {
            events.add(registration.getEvent());
        }
        events.sort(Comparator.comparing(Event::getDateBegining).reversed());

        Set<Event> uniqueEvents = new LinkedHashSet<>(events);

        return uniqueEvents;
    }

    /**
     * Récupère les événements auxquels un membre n'est pas inscrit.
     *
     * @param memberId ID du membre.
     * @return Liste des événements auxquels le membre n'est pas inscrit.
     * @throws UserNonExistent Exception si l'utilisateur n'existe pas.
     * @throws RegistrationNonExistent Exception si l'inscription n'existe pas.
     * @throws EventNonExistant Exception si aucun événement correspondant n'est trouvé.
     */
    @Override
    public List<Event> getEventByNonRegisteredMember(UUID memberId) throws UserNonExistent, RegistrationNonExistent, EventNonExistant {
        User user = userService.getUserById(memberId);
        List<Registration> registrations = registrationService.getRegistrationByUserId(user.getId());
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(memberId);
        }
        List<Event> allEvents = repository.findAll();
        List<Event> nonRegisteredEvents = new ArrayList<>();

        for (Event event : allEvents) {
            boolean isRegistered = false;
            for (Registration registration : registrations) {
                if (registration.getEvent().equals(event)) {
                    isRegistered = true;
                    break;
                }
            }
            if (!isRegistered) {
                nonRegisteredEvents.add(event);
            }
        }

        nonRegisteredEvents.sort(Comparator.comparing(Event::getDateBegining).reversed());

        Set<Event> uniqueEvents = new LinkedHashSet<>(nonRegisteredEvents);
        List<Event> uniqueEventsList = new ArrayList<>(uniqueEvents);

        if(uniqueEventsList.isEmpty()){
            throw new EventNonExistant(memberId);
        }

        return uniqueEventsList;
    }
}
