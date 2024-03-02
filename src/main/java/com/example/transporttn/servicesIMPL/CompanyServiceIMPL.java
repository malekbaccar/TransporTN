package com.example.transporttn.servicesIMPL;

import com.example.transporttn.entites.Account;
import com.example.transporttn.entites.Company;
import com.example.transporttn.entites.Customer;
import com.example.transporttn.enumeration.Role;
import com.example.transporttn.repositories.CompanyRepository;
import com.example.transporttn.repositories.AccountRepository;
import com.example.transporttn.services.ICompanyService;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@AllArgsConstructor
public class CompanyServiceIMPL implements ICompanyService, UserDetailsService {


    private AccountRepository accountRepository;
    private CompanyRepository companyRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");


    private PasswordEncoder passwordEncoder;


    @Override
    public Company registerCompany(Company company) {

            Optional<Account> accountExist = accountRepository.findByEmail(company.getEmail());

            if (accountExist.isPresent()) {
                throw new IllegalArgumentException("Cet account existe déjà.");
            }
            if (company.getEmail() == null || company.getEmail().isEmpty()) {
                throw new IllegalArgumentException("L'email ne peut pas être null");
            }


            // Vérifier si l'email est au bon format
            if (!isValidEmail(company.getEmail())) {
                throw new IllegalArgumentException("L'email n'est pas au bon format");
            }
            Account account = new Account();
            String encodedPassword = passwordEncoder.encode(company.getPassword());
            account.setEmail(company.getEmail());
            account.setPassword(encodedPassword);
            account.setRole(Role.company);
            accountRepository.save(account);
            // Vérifier le rôle et enregistrer dans la table appropriée

                Company company1 = new Company();
                company1.setEmail(company.getEmail());
                company1.setFirstname(company.getFirstname());
                company1.setLastname(company.getLastname());
                company1.setAddress(company.getAddress());
                company1.setNumTEL(company.getNumTEL());
                company1.setAccount(account);

                companyRepository.save(company1);
              return company1;
    }
    // Méthode pour vérifier le format de l'email
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("messageHttpErrorProperties.getError0002()", email)));
        Company company = null;
        try {
            company = companyRepository.findByAccountId(account.getId())
                    .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("messageHttpErrorProperties.getError0002()", account.getId())));
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
}
