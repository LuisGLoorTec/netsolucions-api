package com.netsolucions.netsolucions_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. DEFINIR QUÉ RUTAS ESTÁN PROTEGIDAS
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desactivamos CSRF para facilitar pruebas en Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // "Cualquier petición requiere contraseña"
                )
                .httpBasic(Customizer.withDefaults()) // Usamos autenticación básica (Usuario y Clave)
                .build();
    }

    // 2. CREAR EL USUARIO PARA ENTRAR (EN MEMORIA)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")        // Tu usuario
                .password("{noop}admin123") // Tu contraseña ({noop} es para no encriptarla en pruebas)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}