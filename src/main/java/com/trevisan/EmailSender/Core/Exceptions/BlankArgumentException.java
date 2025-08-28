package com.trevisan.EmailSender.Core.Exceptions;

public class BlankArgumentException extends IllegalArgumentException {
    public BlankArgumentException(){
        super("Cannot send email with values blanks!");
    }
}
