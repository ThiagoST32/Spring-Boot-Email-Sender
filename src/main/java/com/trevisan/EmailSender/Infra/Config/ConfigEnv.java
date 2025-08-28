package com.trevisan.EmailSender.Infra.Config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;

@Component
public class ConfigEnv {
    private final Dotenv dotenv = Dotenv.configure()
            .filename(".env")
            .load();

    private ConfigEnv() {
    }

    @Bean
    protected String getAccessKey() {
        return dotenv.get("ACCESS_KEY");
    }

    @Bean
    protected String getSecretKey() {
        return dotenv.get("SECRET_ACCESS_KEY");
    }

    @Bean
    protected Region getRegion() {
        return Region.of(dotenv.get("REGION"));
    }
}
