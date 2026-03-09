

package com.fees.management.security;

import com.fees.management.entity.User;
import com.fees.management.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public JwtFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Allow auth, h2-console, and swagger endpoints without authentication
        if (path.startsWith("/auth") || 
            path.startsWith("/h2-console") || 
            path.startsWith("/swagger-ui") || 
            path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        // No token → 401
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid token");
            return;
        }

        try {
            String token = authHeader.substring(7);
            String email = JwtUtil.extractEmail(token);
            String role = JwtUtil.extractRole(token);

            User user = userRepository.findByEmail(email)
                    .orElseThrow();

            // Role-based access control
            // SUPER_ADMIN can access everything
            // SCHOOL_ADMIN can manage their school
            // STUDENT has limited access
            if ("DELETE".equals(request.getMethod()) && "STUDENT".equals(role)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Students cannot delete resources");
                return;
            }

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
        }
    }
}

