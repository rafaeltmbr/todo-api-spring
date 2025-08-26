package br.com.rafaeltmbr.todolist.user.core.exceptions;

public class UserException extends Exception {
    private final Type type;

    public UserException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        USER_INVALID_STATE,
        USER_INVALID_EMAIL,
        USER_INVALID_NAME,
        USER_INVALID_PASSWORD,
        USER_INVALID_PASSWORD_HASH,
        USER_NOT_FOUND,
    }
}
