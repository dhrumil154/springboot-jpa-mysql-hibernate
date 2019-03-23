package com.kjit.Diekraft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping
public class ErrorHandlingController extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(InvalidCredentials.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ExceptionResponse errorDetails = new ExceptionResponse("invalid", HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ExceptionResponse>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(EmailAlreadyExist.class)
    public final ResponseEntity<ExceptionResponse> handleEmailAlreadyExist(EmailAlreadyExist e) {
        ExceptionResponse errorDetails = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ExceptionResponse>(errorDetails, HttpStatus.BAD_REQUEST);
    }

	}