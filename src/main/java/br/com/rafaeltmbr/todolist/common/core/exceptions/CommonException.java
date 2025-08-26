package br.com.rafaeltmbr.todolist.common.core.exceptions;

public class CommonException extends Exception {
    private Type type;

    public CommonException(Type type, String message) {
        super(message);
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        COMMON_INVALID_CREATED_AT
    }
}
