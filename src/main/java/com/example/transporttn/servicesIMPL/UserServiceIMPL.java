package com.example.transporttn.servicesIMPL;

import com.example.transporttn.entites.Account;
import com.example.transporttn.entites.Company;
import com.example.transporttn.entites.Customer;
import com.example.transporttn.enumeration.Role;
import com.example.transporttn.repositories.CompanyRepository;
import com.example.transporttn.repositories.CustomerRepository;
import com.example.transporttn.repositories.UserRepository;
import com.example.transporttn.services.IUserService;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;


@Service
@AllArgsConstructor
@Primary

public class UserServiceIMPL implements IUserService, UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private CompanyRepository companyRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    // Pattern pour vérifier le format de l'email
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");

//    public boolean isPasswordExists(String password) {
//        return userRepository.findPassword(password) != null;
//    }

    @Override
    public Account registerUser(Account user) {

        Account existingUser = userRepository.findByEmail2(user.getEmail());

        if (existingUser != null) {
            throw new IllegalArgumentException("Cet email existe déjà.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("L'email ne peut pas être null");
        }


        // Vérifier si l'email est au bon format
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("L'email n'est pas au bon format");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        // Vérifier le rôle et enregistrer dans la table appropriée
        if (user.getRole().equals(Role.customer)) {
            Customer customer = new Customer();
            customer.setEmail(user.getEmail());
            customer.setLastname(user.getLastname());
            customer.setName(user.getName());
            customer.setAccount(user);


            customerRepository.save(customer);
        } else if (user.getRole().equals(Role.company)) {
            Company company = new Company();
            company.setEmail(user.getEmail());
            company.setLastname(user.getLastname());
            company.setName(user.getName());
            company.setAccount(user);
            companyRepository.save(company);
        }

        // Enregistrer l'utilisateur


        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("messageHttpErrorProperties.getError0002()", email)));
        Company company = null;
        try {
            company = companyRepository.findByAccountId(account.getId())
                    .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("messageHttpErrorProperties.getError0002()", account)));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (company == null) {
            throw new UsernameNotFoundException("User not found in database");

        } else {
            System.out.println("user found in database");

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(company.getEmail(), company.getAccount().getPassword(), authorities);
    }

    @Override

    public String loginUser(String email, String password) {
        // Recherche de l'utilisateur par son email dans la base de données
        Account company = userRepository.findByEmail2(email);

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
