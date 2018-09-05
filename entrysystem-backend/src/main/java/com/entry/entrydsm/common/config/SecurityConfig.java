package com.entry.entrydsm.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    //TODO 너무 어려움 Security
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .headers()
//                .disable()
//                .exceptionHandling()
//                .and()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .disable();
//        //-- JWT based authentication
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
