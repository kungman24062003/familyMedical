package com.example.backend.config;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain)
            throws ServletException, IOException {
        
        // Skip for OAuth2 endpoints
        String path = request.getServletPath();
        if (path.startsWith("/oauth2/") || path.startsWith("/login/oauth2/")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = extractToken(request);
            
            if (token != null && jwtUtils.validateToken(token)) {
                String email = jwtUtils.extractEmail(token); // Hoặc extractUsername
                
                Optional<User> userOpt = userService.getUserByEmail(email);
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    user.getAuthorities()
                            );
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                    System.out.println("=== JWT Authentication Successful ===");
                    System.out.println("User: " + user.getEmail());
                    System.out.println("Role: " + user.getRole());
                }
            }
        } catch (Exception e) {
            System.out.println("Error in JWT filter: " + e.getMessage());
            // Không throw exception để request vẫn có thể tiếp tục
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}