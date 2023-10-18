package com.example.oauth2keycloackspringbootexample.config;

import com.example.oauth2keycloackspringbootexample.security.KeycloakJwtToAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/hello").hasAuthority("USER"))
                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(createJwtAuthenticationConverter()))
                )
                .build();
    }

    private JwtAuthenticationConverter createJwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtToAuthoritiesConverter());

        return jwtAuthenticationConverter;
    }
}
