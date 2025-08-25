package br.com.rafaeltmbr.todolist.core.exceptions;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException() {
        super("Invalid email format.");
    }
}
