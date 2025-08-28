package com.trevisan.EmailSender.Core.Exceptions.Validators;

import com.trevisan.EmailSender.Core.Dtos.EmailSenderDto;
import com.trevisan.EmailSender.Core.Exceptions.BlankArgumentException;
import com.trevisan.EmailSender.Core.Exceptions.InvalidEmailException;
import com.trevisan.EmailSender.Core.Exceptions.NullValueOnEmailOrSubject;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class ValidatorSes {

    private boolean isBlankSubject(EmailSenderDto emailSenderDto) {
        return emailSenderDto.subject().isBlank();
    }

    private boolean isNullSubject(EmailSenderDto emailSenderDto) {
        return emailSenderDto.subject() == null;
    }

    private boolean isEmptySubject(EmailSenderDto emailSenderDto){
        return emailSenderDto.subject().isEmpty();
    }

    private boolean isValidEmail(EmailSenderDto emailSenderDto){
        return EmailValidator.getInstance().isValid(emailSenderDto.to());
    }

    private boolean isBlankEmail(EmailSenderDto emailSenderDto){
        return emailSenderDto.to().isBlank();
    }

    private boolean isEmptyEmail(EmailSenderDto emailSenderDto){
        return emailSenderDto.to().isEmpty();
    }

    public void checkEmailSenderDto(EmailSenderDto emailSenderDto){
        //if (!this.isEmptyEmail(emailSenderDto) || !this.isEmptySubject(emailSenderDto)) throw new NullValueOnEmailOrSubject();
        boolean teste= !this.isEmptyEmail(emailSenderDto) || !this.isEmptySubject(emailSenderDto);
        System.err.println(teste);

        //if (!this.isBlankEmail(emailSenderDto) || this.isBlankSubject(emailSenderDto)) throw new BlankArgumentException();
        boolean teste02 = !this.isBlankEmail(emailSenderDto) || this.isBlankSubject(emailSenderDto);
        System.err.println(teste02);

        if (!this.isValidEmail(emailSenderDto)) throw new InvalidEmailException();
        boolean teste03 = !this.isValidEmail(emailSenderDto);
        System.err.println(teste03);
    }
}
