package com.site.dev.security;

import com.site.dev.core.applications.usecases.users.CreateUsersUsecases;
import com.site.dev.services.CustomOAuth2UserService;
import com.site.dev.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private FindUsersUsecases findUsersUsecases;
    private UserMapper userMapper;
    private JwtTokenProvider jwtTokenProvider;
    private CreateUsersUsecases createUsersUsecases;

    @Autowired
    public SecurityConfig(FindUsersUsecases findUsersUsecases, UserMapper userMapper,
                          JwtTokenProvider jwtTokenProvider, CreateUsersUsecases createUsersUsecases) {
        this.findUsersUsecases = findUsersUsecases;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.createUsersUsecases = createUsersUsecases;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtTokenProvider tokenProvider,
                                           CustomUserDetailsService uds,
                                           CustomOAuth2UserService oauth2UserService,
                                           OAuth2SuccessHandler successHandler) throws Exception {

        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(findUsersUsecases,tokenProvider, userMapper);

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/oauth2/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(u -> u.userService(oauth2UserService))
                        .successHandler(successHandler)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
