package com.trevisan.EmailSender.Core.Exceptions.Exceptions;

import software.amazon.awssdk.services.ses.model.SesException;

public class InvalidEmailException extends SesException {
    public InvalidEmailException() {
        super(builder().message("Invalid Email!"));
    }
}
