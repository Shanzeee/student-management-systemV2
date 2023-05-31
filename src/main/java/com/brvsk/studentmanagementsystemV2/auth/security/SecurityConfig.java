package com.brvsk.studentmanagementsystemV2.auth.security;

import com.brvsk.studentmanagementsystemV2.auth.CustomAuthenticationFilter;
import com.brvsk.studentmanagementsystemV2.auth.CustomAuthorizationFilter;
import com.brvsk.studentmanagementsystemV2.auth.appuser.AppUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin();
//        http.authorizeRequests().antMatchers(POST,"/api/v1/departments/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST,"/api/v1/groups/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(DELETE,"/api/v1/groups/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST,"/api/v1/courses/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST,"/api/v1/teachers/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(GET, "/api/v1/users/**").hasAnyAuthority("ROLE_ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

}