package br.com.rafaeltmbr.todolist.todo.core.exceptions;

import br.com.rafaeltmbr.todolist.common.core.exceptions.DomainException;

public class TodoNotFoundException extends DomainException {
    public TodoNotFoundException() {
        super("Todo not found.");
    }
}
