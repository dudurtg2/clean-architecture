package com.site.dev.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.site.dev.adapter.mappers.UserMapper;
import com.site.dev.core.applications.usecases.users.FindUsersUsecases;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final FindUsersUsecases findUsersUsecases;
    private final UserMapper userMapper;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, FindUsersUsecases findUsersUsecases, UserMapper userMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.findUsersUsecases = findUsersUsecases;
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = this.recoverToken(request);
            UserDetails userDetails = null;

            if (token != null) {
                String subject = jwtTokenProvider.validateAccessToken(token);
                if (subject != null) {
                    userDetails = userMapper.toUserEntity(findUsersUsecases.execute(subject));
                }
            } else {
                String apiKey = this.recoverApiKey(request);
                if (apiKey != null) {
                    userDetails = jwtTokenProvider.validateApiKey(apiKey);
                }
            }

            if (userDetails != null) {
                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            // Logar o erro pode ser útil para depuração
            logger.error("Falha no processo de autenticação no filtro.", e);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }

    private String recoverApiKey(HttpServletRequest request) {
        return request.getHeader("X-API-Key");
    }
}