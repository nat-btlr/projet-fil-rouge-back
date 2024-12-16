package fr.filrougeback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// avec url = http://localhost:8080/bonjour
    // org.springframework.web.bind.MissingServletRequestParameterException
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrorMessage handleParameterNotFoundException(MissingServletRequestParameterException ex) {
    	System.out.println("Handling MissingServletRequestParameterException: " + ex.getMessage());
    	return new APIErrorMessage(404, "404 Error", ex.getMessage());
    }

    
    // avec url = http://localhost:8080/exception500
    // getmapping pour lever une exception
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIErrorMessage unknownException(Exception ex) {
        return new APIErrorMessage(500, "Erreur interne du serveur", ex.getMessage());
    }
    
    // avec url = http://localhost:8080/public/login
    // getmapping pour lever une exception
    @ExceptionHandler(value = { UserDoesNotExistException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrorMessage handleUserNotFoundException(UserDoesNotExistException ex) {
        return new APIErrorMessage(404, "The user does not exist", ex.getMessage());
    }
}
