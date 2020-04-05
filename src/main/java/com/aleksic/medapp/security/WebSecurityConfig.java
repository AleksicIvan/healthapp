package com.aleksic.medapp.security;

import com.aleksic.medapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("customerUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  userRepository))
                .authorizeRequests()
                // configure access rules
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/api/healthchecks") .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/doctors") .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/hospitals") .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/hospitals") .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/hospitals/{id}") .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/specializations") .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/specializations") .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/specializations") .hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/specializations") .hasRole("ADMIN")
                .antMatchers("/api/users/{id}") .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/users") .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/users") .permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();
        //                .formLogin()
//                .and()
//                .rememberMe().tokenValiditySeconds(2592000);
    }

}
