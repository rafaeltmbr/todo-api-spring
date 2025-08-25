package br.com.rafaeltmbr.todolist.core.exceptions;

public class TodoNotFoundException extends DomainException {
    public TodoNotFoundException() {
        super("Todo not found.");
    }
}
