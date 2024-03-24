package dev.memocode.question_server.security.config;

import dev.memocode.question_server.security.handler.CustomAccessDeniedHandler;
import dev.memocode.question_server.security.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
                .cors(c -> c.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(a -> a
                        .requestMatchers(HttpMethod.GET,"/questions/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/questions").access(hasScope("write:question"))
                        .requestMatchers(HttpMethod.PATCH,"/questions/**").access(hasScope("write:question"))
                        .requestMatchers(HttpMethod.DELETE,"/questions/**").access(hasScope("write:question"))
                        .requestMatchers(HttpMethod.GET,"/questions/**").permitAll()
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
