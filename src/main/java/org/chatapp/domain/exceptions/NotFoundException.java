package org.chatapp.domain.exceptions;

public class NotFoundException extends DomainException {
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String format, Object... args){
        super(String.format(format, args));
    }
}
