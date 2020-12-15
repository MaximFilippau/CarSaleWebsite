package com.filippau.tc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderBean {
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
