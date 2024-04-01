package ru.gb.springbootlesson3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("ui/books").permitAll()
                        .requestMatchers("reader/**").authenticated()
                        .requestMatchers("issue/**", "ui/issues").hasAuthority("admin"))
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
