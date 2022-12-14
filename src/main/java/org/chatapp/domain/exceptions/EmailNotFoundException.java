package org.chatapp.domain.exceptions;

public class EmailNotFoundException extends NotFoundException {
    public EmailNotFoundException(String email){
        super(new StringBuilder().append("Email could not be found: ").append(email).toString());
    }

    public EmailNotFoundException(String format, Object... args){
        super(String.format(format, args));
    }
}
