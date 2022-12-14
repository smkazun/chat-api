package org.chatapp.domain.exceptions;

public class AlreadyInUseException extends DomainException{

    public AlreadyInUseException(String email) {
        super(new StringBuilder().append("Email already in use: ").append(email).toString());
    }

    public AlreadyInUseException(String format, Object... args){
        super(String.format(format, args));
    }
}
