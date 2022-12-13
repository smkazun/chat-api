package org.chatapp.domain.exceptions;

public class AlreadyInUseException extends DomainException{

    public AlreadyInUseException(String message) {
        super(message);
    }

    public AlreadyInUseException(String format, Object... args){
        super(String.format(format, args));
    }
}
