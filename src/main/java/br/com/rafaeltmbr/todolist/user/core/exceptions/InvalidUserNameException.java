package br.com.rafaeltmbr.todolist.user.core.exceptions;

import br.com.rafaeltmbr.todolist.common.core.exceptions.DomainException;

public class InvalidUserNameException extends DomainException {
    public InvalidUserNameException() {
        super("User name must be between 3 to 64 characters long.");
    }
}
