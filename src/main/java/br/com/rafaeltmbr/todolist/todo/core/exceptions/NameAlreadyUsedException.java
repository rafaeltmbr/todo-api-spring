package br.com.rafaeltmbr.todolist.todo.core.exceptions;

import br.com.rafaeltmbr.todolist.common.core.exceptions.DomainException;

public class NameAlreadyUsedException extends DomainException {
    public NameAlreadyUsedException() {
        super("Name already used.");
    }
}
