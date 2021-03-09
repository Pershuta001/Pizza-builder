package com.example.pizzabuilder.settings.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if(token == null || token.length()==0){
            filterChain.doFilter(request,response);
            return;
        }
        try{
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor("pizzabuildersecurecodepizzabuildersecurecode".getBytes()))
                    .build();
            Claims body = parser.parseClaimsJws(token).getBody();
            String userEmail = body.getSubject();
            List<Map<String, String>> authorities =
                    (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> authority = authorities
                    .stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userEmail,
                    null,
                    authority
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e){
            throw new IllegalStateException("Token can`t be trusted: " + token);
        }
        filterChain.doFilter(request,response);
    }
}
