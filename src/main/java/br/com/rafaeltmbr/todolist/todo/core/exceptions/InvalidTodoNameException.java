package br.com.rafaeltmbr.todolist.todo.core.exceptions;

import br.com.rafaeltmbr.todolist.common.core.exceptions.DomainException;

public class InvalidTodoNameException extends DomainException {
    public InvalidTodoNameException(String message) {
        super(message);
    }
}
