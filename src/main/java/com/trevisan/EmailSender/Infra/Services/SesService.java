package com.trevisan.EmailSender.Infra.Services;

import com.trevisan.EmailSender.Adapters.EmailSenderGateway;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class SesService implements EmailSenderGateway {
    private final SesClient sesClient;
    private String bodyHtml = "";

    @Autowired
    public SesService(SesClient sesClient) {
        this.sesClient = sesClient;
    }

    @SneakyThrows
    @Override
    public String senderEmail(String to, String subject) {
        this.readHtmlFile();
        send(sesClient, to, subject);
        sesClient.close();
        return "Email enviado!";
    }

    private void send(SesClient sesClient, String to, String subject) {
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content contentSubject = Content.builder()
                .data(subject)
                .build();

        Content contentHtml = Content.builder()
                .data(bodyHtml)
                .build();

        Body body = Body.builder()
                .text(contentSubject)
                .html(contentHtml)
                .build();

        Message message = Message.builder()
                .subject(contentSubject)
                .body(body)
                .build();


        var emailRequest = this.sendEmailRequest(message, destination, to);
        sesClient.sendEmail(emailRequest);
    }

    private SendEmailRequest sendEmailRequest(Message message, Destination destination, String to) {
        return SendEmailRequest.builder()
                .message(message)
                .destination(destination)
                .source(to)
                .build();
    }

    private void readHtmlFile() throws IOException {
        bodyHtml = Files.readString(Path.of("src/main/java/com/trevisan/EmailSender/Email/index.html"));
    }
}
