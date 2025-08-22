package com.trevisan.EmailSender.Infra.Config;

import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SesConfig {
    @Bean
    public BasicAWSCredentials configureBasicCredentials(){
        return new BasicAWSCredentials()

    }
}
