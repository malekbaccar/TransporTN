package com.example.transporttn.servicesIMPL;

import com.example.transporttn.entites.Account;
import com.example.transporttn.entites.Company;
import com.example.transporttn.repositories.CompanyRepository;
import com.example.transporttn.repositories.UserRepository;
import com.example.transporttn.services.ICompanyService;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class CompanyServiceIMPL implements ICompanyService, UserDetailsService {


    private UserRepository userRepository;
    private CompanyRepository companyRepository;
   

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

}
