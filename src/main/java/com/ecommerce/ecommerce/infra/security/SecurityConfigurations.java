package com.ecommerce.ecommerce.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfigurations{

    private final SecurityFilter securityFilter;

    @Autowired
    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // Usuarios
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/cadastrar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/visualizarTodos").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/visualizar").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/deletar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/atualizar").hasRole("ADMIN")

                        // Produtos
                        .requestMatchers(HttpMethod.POST, "/api/produtos/cadastrar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/produtos/visualizarTodos").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/produtos/visualizar").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/produtos/deletar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/produtos/atualizar").hasRole("ADMIN")

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}