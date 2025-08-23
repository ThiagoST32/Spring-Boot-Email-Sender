package com.trevisan.EmailSender.Infra.Config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigEnv {
    private final Dotenv dotenv = Dotenv.configure()
            .filename(".env")
            .load();

    private ConfigEnv(){}

    @Bean
    protected String getAccessKey(){
        return dotenv.get("ACCESS_KEY");
    }

    @Bean
    protected String getSecretKey(){
        return dotenv.get("SECRET_ACCESS_KEY");
    }

    @Bean
    protected String getRegion(){
        return dotenv.get("REGION");
    }
}
