package com.trevisan.EmailSender.Core.UseCases;

public interface EmailSenderUseCase {
    String sendEmail(String to, String subject);
}
