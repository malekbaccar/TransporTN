package com.example.transporttn.servicesIMPL;

import com.example.transporttn.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceIMPL {
    private CustomerRepository customerRepository;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Account comptes = customerRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("messageHttpErrorProperties.getError0002()", email)));
//        Personnel personnel = null;
//        try {
//            personnel = personnelRepository.findPersonnelByCompteId(comptes.getId())
//                    .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("messageHttpErrorProperties.getError0002()", comptes)));
//        } catch (ResourceNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (personnel == null) {
//            throw new UsernameNotFoundException("User not found in database");
//        } else if (comptes.isEnVeille()) {
//            throw new UsernameNotFoundException("User is in Veille");
//        } else {
//            System.out.println("user found in database");
//            comptes.setDatelastlogin(new Date());
//            comptesRepository.save(comptes);
//        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(personnel.getCompte().getRole().getNom()));
//        return new org.springframework.security.core.userdetails.User(personnel.getCompte().getEmail(), personnel.getCompte().getPassword(), authorities);
//    }
}
