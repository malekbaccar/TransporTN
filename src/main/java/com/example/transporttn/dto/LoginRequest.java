package com.example.transporttn.dto;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest  {
    private String email;
    private String password;
}
