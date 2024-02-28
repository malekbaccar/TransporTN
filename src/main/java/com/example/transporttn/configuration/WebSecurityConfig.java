package com.example.transporttn.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // @Autowired
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.addAllowedOriginPattern("*");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()



                .and()
                .csrf()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error");
      /*  http.cors();
        http.authorizeRequests()

                .antMatchers("/api/users**")
                .permitAll() // accès pour tous users
                // .antMatchers("/provider/**").hasAuthority("ADMIN")
                .antMatchers("/role")
                .hasAuthority("USER")
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true") // fixer la page login
                .defaultSuccessUrl("/dashboard") // page d'accueil après login avec succès
                .usernameParameter("email") // paramètres d'authentifications login et password
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout")) // route de deconnexion ici/logut
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling() // une fois deconnecté redirection vers login
                .accessDeniedPage("/403");*/
    }

    // laisser l'accès aux ressources
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }
}
