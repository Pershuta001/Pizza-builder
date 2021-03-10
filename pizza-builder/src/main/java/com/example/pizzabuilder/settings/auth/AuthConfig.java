package com.example.pizzabuilder.settings.auth;

import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.settings.jwt.JWTAuthenticationFilter;
import com.example.pizzabuilder.settings.jwt.JwtConfig;
import com.example.pizzabuilder.settings.jwt.JwtTokenVerifier;
import com.example.pizzabuilder.sevices.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserService applicationUserService;
    private final JwtConfig jwtConfig;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), applicationUserService))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig), JWTAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/secured-api").hasRole(RolesEnum.USER.name())
                .antMatchers("/secured-api-admin").hasRole(RolesEnum.ADMIN.name())
                .antMatchers("/", "/sign-up")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
