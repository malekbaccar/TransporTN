package com.example.transporttn;

import com.example.transporttn.entites.Role;
import com.example.transporttn.repositories.RoleRepository;
import com.example.transporttn.services.IRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TransportTnApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportTnApplication.class, args);
    }

/*    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
	@Bean
    CommandLineRunner run(IRoleService rolesService){
		return args -> {
            Role role = new Role();
            role.setName("ADMIN");
            rolesService.createNewRole(role);
            role = new Role();
            role.setName("COMPANY");
            rolesService.createNewRole(role);
            role = new Role();
            role.setName("CUSTOMER");
            rolesService.createNewRole(role);
		};
	}
}
