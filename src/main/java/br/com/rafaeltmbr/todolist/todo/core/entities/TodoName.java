package br.com.rafaeltmbr.todolist.todo.core.entities;

import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

public class TodoName {
    private String value;

    public TodoName(String value) throws TodoException {
        this.setValue(value);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) throws TodoException {
        if (value == null) {
            throw new TodoException(TodoException.Type.TODO_INVALID_NAME, "Todo name value must not be null.");
        }

        this.value = value.trim();
        var minimum = 3;
        var maximum = 64;

        if (this.value.length() < minimum || this.value.length() > maximum) {
            throw new TodoException(TodoException.Type.TODO_INVALID_NAME, "Todo name must be between " + minimum + " to " + maximum + " characters long.");
        }
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (this.getClass() != obj.getClass()) return false;

        var other = (TodoName) obj;

        return value.equals(other.value);
    }
}
