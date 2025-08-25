package br.com.rafaeltmbr.todolist.todo.core.exceptions;

import br.com.rafaeltmbr.todolist.common.core.exceptions.DomainException;

public class InvalidTodoException extends DomainException {
    public InvalidTodoException(String name) {
        super(name);
    }
}
