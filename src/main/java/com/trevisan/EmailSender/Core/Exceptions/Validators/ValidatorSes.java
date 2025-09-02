package com.trevisan.EmailSender.Core.Exceptions.Validators;

import com.trevisan.EmailSender.Core.Dtos.EmailSenderDto;
import com.trevisan.EmailSender.Core.Exceptions.Exceptions.BlankArgumentException;
import com.trevisan.EmailSender.Core.Exceptions.Exceptions.InvalidEmailException;
import com.trevisan.EmailSender.Core.Exceptions.Exceptions.NullValueOnEmailOrSubject;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class ValidatorSes {

    private boolean isBlankSubject(EmailSenderDto emailSenderDto) {
        return emailSenderDto.subject().isBlank();
    }

    private boolean isBlankEmail(EmailSenderDto emailSenderDto) {
        return emailSenderDto.to().isBlank();
    }

    private boolean isEmptySubject(EmailSenderDto emailSenderDto) {
        return emailSenderDto.subject().isEmpty();
    }

    private boolean isValidEmail(EmailSenderDto emailSenderDto) {
        return EmailValidator.getInstance().isValid(emailSenderDto.to());
    }

    private boolean isEmptyEmail(EmailSenderDto emailSenderDto) {
        return emailSenderDto.to().isEmpty();
    }

    public void checkEmailSenderDto(EmailSenderDto emailSenderDto) {
        if (this.isEmptySubject(emailSenderDto) || this.isEmptyEmail(emailSenderDto))
            throw new NullValueOnEmailOrSubject();

        if (this.isBlankEmail(emailSenderDto) || this.isBlankSubject(emailSenderDto))
            throw new BlankArgumentException();

        if (!this.isValidEmail(emailSenderDto))
            throw new InvalidEmailException();
    }
}
