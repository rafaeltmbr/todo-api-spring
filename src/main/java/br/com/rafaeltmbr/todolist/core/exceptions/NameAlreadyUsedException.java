package br.com.rafaeltmbr.todolist.core.exceptions;

public class NameAlreadyUsedException extends DomainException {
    public NameAlreadyUsedException() {
        super("Name already used.");
    }
}
