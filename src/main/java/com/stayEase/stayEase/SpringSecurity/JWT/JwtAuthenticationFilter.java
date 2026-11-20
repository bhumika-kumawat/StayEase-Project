
package com.stayEase.stayEase.SpringSecurity.JWT;

import com.stayEase.stayEase.SpringSecurity.CustomUserDetailsService.CustomUserDetailsService;
import com.stayEase.stayEase.SpringSecurity.Jwt.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Step 1: Read Authorization header
        String authHeader = request.getHeader("Authorization");

        // Step 2: Check whether Bearer token exists
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // Step 3: Extract JWT
            String token = authHeader.substring(7);

            // Step 4: Extract email from JWT
            String email = jwtService.extractEmail(token);

            // Step 5: Check existing authentication
            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                // Step 6: Load user from database
                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(email);

                // Step 7: Validate JWT
                if (jwtService.isTokenValid(token, userDetails.getUsername())) {

                    // Step 8: Create authentication object
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // Step 9: Store authentication
                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }
        }

        // Step 10: Continue filter chain
        filterChain.doFilter(request, response);
    }
}
