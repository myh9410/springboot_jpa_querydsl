package com.springboot.board.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile("!test")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)      //.csrf().disable()
                .httpBasic(AbstractHttpConfigurer::disable) //.httpBasic().disable()

                .cors(Customizer.withDefaults())            //.cors().and()

                .authorizeHttpRequests(request ->           //.authorizeHttpRequests()
                        request
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                )
        ;

        return http.build();
    }
}
