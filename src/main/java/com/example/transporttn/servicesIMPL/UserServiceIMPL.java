package com.example.transporttn.servicesIMPL;

import com.example.transporttn.entites.Account;
import com.example.transporttn.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
@AllArgsConstructor

public class UserServiceIMPL {
    @Autowired
    private UserRepository userRepository;


    @Autowired

    private PasswordEncoder passwordEncoder;
    // Pattern pour vérifier le format de l'email
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");


    public Account registerUser(Account user) {

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("L'email ne peut pas être null");

        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null");
        }
        // Vérifier si l'email est au bon format
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("L'email n'est pas au bon format");
        }
        // Crypter le mot de passe
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Enregistrer l'utilisateur
        userRepository.save(user);

        return user;
    }

    public String loginUser(String email, String password) {
        // Recherche de l'utilisateur par son email dans la base de données
        Account company = userRepository.findByEmail(email);

        // Vérification si l'utilisateur existe
        if (company == null) {
            return ("utilisateur non trouvée"); // Utilisateur non trouvé
        }

        // Vérification si le mot de passe est null
        if (password == null) {
            return ("votre mot de passe est null"); // Mot de passe invalide
        }


        // Comparaison des mots de passe (utilisation de BCryptPasswordEncoder)
        if (passwordEncoder.matches(password, company.getPassword())) {
            return ("Authentification réussie"); // Authentification réussie
        } else {
            return ("Mot de passe incorrect"); // Mot de passe incorrect
        }


    }

    // Méthode pour vérifier le format de l'email
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
