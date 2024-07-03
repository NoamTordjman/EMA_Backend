package com.example.demo.Services.Impl;

import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import com.example.demo.exception.UserNonExistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service implémentant la gestion des utilisateurs.
 * Ce service offre des fonctionnalités pour créer, mettre à jour, supprimer, récupérer des utilisateurs, ainsi que pour gérer leur connexion.
 */
@Service
public class UserServiceImpl implements UserServices {

    private final UserRepository repository;

    /**
     * Constructeur pour l'injection de dépendances du repository des utilisateurs.
     *
     * @param repository Le repository d'accès aux données des utilisateurs.
     */
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Crée un nouvel utilisateur à partir des données fournies.
     *
     * @param UserDTO Les données de l'utilisateur à créer.
     * @return L'utilisateur créé.
     * @throws UserNonExistent Exception si les données de l'utilisateur sont incomplètes ou incorrectes.
     */
    @Override
    public User CreateUser(UserDTOCreate UserDTO) throws UserNonExistent {
        User user = new User();
        user.setMail(UserDTO.getMail());
        user.setName(UserDTO.getName());
        user.setSurname(UserDTO.getSurname());
        user.setPassword(UserDTO.getPassword());
        return repository.save(user);
    }

    /**
     * Supprime un utilisateur spécifié par son ID.
     *
     * @param idUser L'ID de l'utilisateur à supprimer.
     * @throws UserNonExistent Exception si l'utilisateur n'existe pas.
     */
    @Override
    public void deleteUser(UUID idUser) throws UserNonExistent {
        User user = getUserById(idUser);  // Vérifie si l'utilisateur existe avant de procéder à la suppression.
        repository.deleteById(idUser);
    }

    /**
     * Met à jour les informations d'un utilisateur.
     *
     * @param userDTO Le DTO contenant les informations à mettre à jour.
     * @return L'utilisateur mis à jour.
     * @throws UserNonExistent Exception si l'utilisateur spécifié n'existe pas.
     */
    @Override
    public User updateUser(UserDTOUpdate userDTO) throws UserNonExistent {
        User user = getUserById(userDTO.getUserid());
        user.setMail(userDTO.getMail());
        return repository.save(user);
    }

    /**
     * Récupère tous les utilisateurs enregistrés.
     *
     * @return Liste de tous les utilisateurs.
     */
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    /**
     * Récupère un utilisateur par son ID.
     *
     * @param idUser L'ID de l'utilisateur à retrouver.
     * @return L'utilisateur correspondant à l'ID fourni.
     * @throws UserNonExistent Exception si l'utilisateur n'existe pas.
     */
    @Override
    public User getUserById(UUID idUser) throws UserNonExistent {
        return repository.findById(idUser).orElseThrow(() -> new UserNonExistent(idUser));
    }

    /**
     * Authentifie un utilisateur basé sur son nom d'utilisateur et son mot de passe.
     *
     * @param username Le nom d'utilisateur.
     * @param password Le mot de passe.
     * @return L'utilisateur authentifié si les identifiants sont valides.
     */
    @Override
    public User Login(String username, String password) {
        return repository.Login(username, password);
    }
}
