package com.trevisan.EmailSender.Infra.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
public class SesConfig {

    @Autowired
    private ConfigEnv env;

    @Bean("SesCredentials")
    public AwsBasicCredentials configureBasicCredentials(){
        return AwsBasicCredentials.builder()
                .accessKeyId(env.getAccessKey())
                .secretAccessKey(env.getSecretKey())
                .build();
    }

    @Bean("SesClient")
    public SesClient sesClient(){
        return SesClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create())
                .build();
    }
}
