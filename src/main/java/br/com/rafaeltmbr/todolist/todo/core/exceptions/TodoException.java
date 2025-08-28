package br.com.rafaeltmbr.todolist.todo.core.exceptions;

public class TodoException extends Exception {
    private final Type type;

    public TodoException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        TODO_MISSING_DEPENDENCIES,
        TODO_INVALID_STATE,
        TODO_INVALID_NAME,
        TODO_NAME_ALREADY_USED,
        TODO_NOT_FOUND
    }
}
