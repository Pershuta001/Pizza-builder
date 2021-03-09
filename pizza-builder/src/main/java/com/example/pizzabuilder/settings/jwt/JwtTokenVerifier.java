package com.example.pizzabuilder.settings.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String tokenWithPrefix = request.getHeader("Authorization");

        if(tokenWithPrefix == null || tokenWithPrefix.length()==0 || !tokenWithPrefix.startsWith(jwtConfig.TOKEN_PREFIX)){
            filterChain.doFilter(request,response);
            return;
        }
        try{
            String token = tokenWithPrefix.replaceFirst(jwtConfig.TOKEN_PREFIX,"");
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.signingSecretKey())
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
            throw new IllegalStateException("Token can`t be trusted: " + tokenWithPrefix);
        }
        filterChain.doFilter(request,response);
    }
}
