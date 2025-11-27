package com.pragma.powerup.foodcourtmicroservice.infrastructure.configuration.security;

import com.pragma.powerup.foodcourtmicroservice.infrastructure.security.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/restaurants").hasAnyRole("ADMINISTRADOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurants").hasAnyRole("CLIENTE", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/dishes/restaurant/**").hasAnyRole("CLIENTE", "CLIENT")
                        .requestMatchers(HttpMethod.POST, "/dishes").hasAnyRole("PROPIETARIO", "OWNER")
                        .requestMatchers(HttpMethod.PUT, "/dishes/{id}").hasAnyRole("PROPIETARIO", "OWNER")
                        .requestMatchers(HttpMethod.PUT, "/dishes/{id}/active").hasAnyRole("PROPIETARIO", "OWNER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
