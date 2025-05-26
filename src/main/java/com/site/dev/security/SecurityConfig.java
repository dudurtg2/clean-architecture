package com.site.dev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final SecurityFilter securityFilter;
    private final FindUsersUsecases findUsersUsecases;
    private final UserMapper userMapper;

    @Autowired
    public SecurityConfig(SecurityFilter securityFilter,
                                 FindUsersUsecases findUsersUsecases,
                                 UserMapper userMapper) {
        this.securityFilter      = securityFilter;
        this.findUsersUsecases   = findUsersUsecases;
        this.userMapper          = userMapper;
    }


    @Bean
    @Order(1)
    public SecurityFilterChain oauth2Chain(HttpSecurity http) throws Exception {
        http
                // aplica apenas a estes endpoints
                .securityMatcher("/oauth2/**", "/login/oauth2/**", "/api/user/login/google")
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        // URIs padrão do OAuth2
                        .authorizationEndpoint(endp -> endp.baseUri("/oauth2/authorization"))
                        .redirectionEndpoint(redir -> redir.baseUri("/login/oauth2/code/*"))
                        // quem for bem-sucedido cai neste seu controller:
                        .defaultSuccessUrl("/api/user/login/google", true)
                );
        return http.build();
    }

    //
    // 2) Chain para seus endpoints /api/** protegidos por JWT
    //
    @Bean
    @Order(2)
    public SecurityFilterChain jwtChain(HttpSecurity http) throws Exception {
        http
                // só pega /api/**
                .securityMatcher("/api/**")
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                // sem sessão, JWT stateless
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // libera login tradicional, refresh e criação de usuário
                        .requestMatchers(
                                "/api/user/login",
                                "/api/user/refresh-token",
                                "/api/user/create"
                        ).permitAll()

                        // recursos premium
                        .requestMatchers("/api/coins/**", "/api/movements/**")
                        .hasRole("PREMIUM")

                        // todo o resto exige JWT válido
                        .anyRequest().authenticated()
                )
                // seu filtro que lê o Bearer token
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //
    // beans de autenticação / userDetails / senha / manager
    //

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var entity = findUsersUsecases.execute(username.toLowerCase());
            if (entity == null) {
                throw new UsernameNotFoundException("Usuário não encontrado");
            }
            return userMapper.toUserEntity(entity);
        };
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(authenticationProvider());
        return builder.build();
    }
}
