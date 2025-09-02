package com.trevisan.EmailSender.Core.Exceptions.Exceptions;

public class NullValueOnEmailOrSubject extends NullPointerException {
    public NullValueOnEmailOrSubject(){
        super("Values cannot be null!");
    }
}
