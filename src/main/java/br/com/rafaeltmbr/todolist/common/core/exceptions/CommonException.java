package br.com.rafaeltmbr.todolist.common.core.exceptions;

public class CommonException extends Exception {
    private final Type type;

    public CommonException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        COMMON_INVALID_CREATED_AT,
        COMMON_INVALID_ID
    }
}
