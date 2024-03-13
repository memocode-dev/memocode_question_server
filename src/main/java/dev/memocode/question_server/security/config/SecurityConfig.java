package dev.memocode.question_server.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(a -> a
                        .requestMatchers(HttpMethod.GET,"/questions/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/questions").access(hasScope("write:question"))
                        .requestMatchers(HttpMethod.PATCH,"/questions/**").access(hasScope("write:question"))
                        .requestMatchers(HttpMethod.DELETE,"/questions/**").access(hasScope("write:question"))
                        .requestMatchers(HttpMethod.GET,"/questions").permitAll()
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
