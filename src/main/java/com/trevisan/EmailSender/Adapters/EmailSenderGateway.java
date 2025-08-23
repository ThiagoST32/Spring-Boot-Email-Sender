package com.trevisan.EmailSender.Adapters;

public interface EmailSenderGateway {
    String senderEmail(String to, String subject);
}
