package com.cghue.projecthousemaidwebapp.config;

import com.cghue.projecthousemaidwebapp.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    private final UserDetailsService userDetailsService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SpringSecurity(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/auths/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/auths/**").permitAll()
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/api/categories/**").permitAll()
                                .requestMatchers("/api/categories/jobs/**").permitAll()
                                .requestMatchers("/api/categories").permitAll()
                                .requestMatchers("/api/jobs/**").permitAll()
                                .requestMatchers("/confirm").hasRole("USER")
                                .requestMatchers("/api/users/current-user/**").permitAll()
                                .requestMatchers("/api/orders/info-order/**").hasRole("USER")
                                .requestMatchers("/api/users/**").permitAll()
                                .requestMatchers("/api/dash-boards/**").permitAll()


//                                .requestMatchers(HttpMethod.POST,"/api/orders").permitAll()
//                                .requestMatchers("/api/ratings").permitAll()
//                                .requestMatchers(HttpMethod.GET,"/api/jobs").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/jobs").permitAll()
//                                .requestMatchers(HttpMethod.PUT,"/api/jobs/*").permitAll()
////                                .requestMatchers("/api/test").hasRole("USER")
//                                .requestMatchers(HttpMethod.GET,"/api/orders").hasAnyRole("ADMIN","USER")
                                .anyRequest().authenticated())
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new RestAuthenticationEntryPoint());
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

}