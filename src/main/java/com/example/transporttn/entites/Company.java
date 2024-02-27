package com.example.transporttn.entites;

import com.example.transporttn.enumeration.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;



@Entity
@Table(name = "_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "numeroTEL")
    private String numeroTEL;

    @Column(name = "addresse")
    private String addresse;

    @Column(name = "mot_de_passe")
    private String motDePasse;
    private Role role;

    @OneToOne(mappedBy = "user")
    private Conducteur conducteur; // Chauffeur associé à cet utilisateur

    @OneToMany(mappedBy = "user")
    private List<reclamation> reclamations; // Liste des réclamations de cet utilisateur

    @OneToMany(mappedBy = "user")
    private List<Vehicule> vehicles; // Liste des véhicules de cet utilisateur

    @OneToMany(mappedBy = "user")
    private List<lieu> lieus; // Liste des lieux associés à cet utilisateur

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
