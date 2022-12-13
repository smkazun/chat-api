package org.chatapp.domain.exceptions;

/**
 * Decouple RuntimeException from other exceptions
 */
public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
