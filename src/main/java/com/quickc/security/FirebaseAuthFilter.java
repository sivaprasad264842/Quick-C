package com.quickc.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FirebaseAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        // 🔓 PUBLIC FILES & PAGES
        if (path.startsWith("/html/login.html")
                || path.startsWith("/auth")
                || path.startsWith("/css")
                || path.startsWith("/js")
                || path.startsWith("/images")
                || path.endsWith(".css")
                || path.endsWith(".js")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendRedirect("/html/login.html");
            return;
        }

        try {
            String token = authHeader.substring(7);
            FirebaseToken decodedToken
                    = FirebaseAuth.getInstance().verifyIdToken(token);

            request.setAttribute("uid", decodedToken.getUid());
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.sendRedirect("/html/login.html");
        }
    }
}