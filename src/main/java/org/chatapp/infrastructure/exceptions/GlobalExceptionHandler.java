package org.chatapp.infrastructure.exceptions;

import org.chatapp.domain.exceptions.AlreadyInUseException;
import org.chatapp.domain.exceptions.NotFoundException;
import org.chatapp.infrastructure.data.entities.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={NotFoundException.class})
    ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={AlreadyInUseException.class})
    ResponseEntity<ApiResponse> handleAlreadyInUserException(AlreadyInUseException ex){
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
