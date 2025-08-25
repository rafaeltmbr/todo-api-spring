package br.com.rafaeltmbr.todolist.user.core.exceptions;

import br.com.rafaeltmbr.todolist.common.core.exceptions.DomainException;

public class InvalidUserPasswordException extends DomainException {
    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
