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

    @Bean("SesClient")
    public SesClient sesClientConfig(){
        AwsBasicCredentials credentials = AwsBasicCredentials.builder()
                .accessKeyId(env.getAccessKey())
                .secretAccessKey(env.getSecretKey())
                .build();

        return SesClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(env.getRegion())
                .build();
    }
}
