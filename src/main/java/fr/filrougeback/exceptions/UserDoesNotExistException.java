package fr.filrougeback.exceptions;

import lombok.Getter;

@Getter
public class UserDoesNotExistException extends RuntimeException {

private static final long serialVersionUID = -4001785457990592779L;
private String message;

    public UserDoesNotExistException() {}

    public UserDoesNotExistException(String msg) {
        super(msg);
        this.message = msg;
    }

}