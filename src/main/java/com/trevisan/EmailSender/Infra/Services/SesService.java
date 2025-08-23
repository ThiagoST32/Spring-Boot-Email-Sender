package com.trevisan.EmailSender.Infra.Services;

import com.trevisan.EmailSender.Adapters.EmailSenderGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;


@Service
public class SesService implements EmailSenderGateway {
    private final SesClient sesClient;
    private final String bodyHtml = "";

    @Autowired
    public SesService(SesClient sesClient) {
        this.sesClient = sesClient;
    }

    @Override
    public String senderEmail(String to, String subject) {
        send(sesClient, to, subject, bodyHtml);
        sesClient.close();
        return "Email enviado!";
    }

    private static void send(SesClient sesClient, String to, String subject, String messageHtml) {
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content contentSubject = Content.builder()
                .data(subject)
                .build();

        Content contentHtml = Content.builder()
                .data(messageHtml)
                .build();

        Body body = Body.builder()
                .text(contentSubject)
                .html(contentHtml)
                .build();

        Message message = Message.builder()
                .subject(contentSubject)
                .build();
    }

    private void sendEmail(Destination destination, Message message) {
        sesClient.sendEmail(
                new SendEmailRequest.Builder()
                .destination(destination)
                .source()
                .message(message)
                .build());
    }
}
