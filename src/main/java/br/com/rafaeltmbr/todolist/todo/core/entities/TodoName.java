package br.com.rafaeltmbr.todolist.todo.core.entities;

import br.com.rafaeltmbr.todolist.todo.core.exceptions.InvalidTodoNameException;

public class TodoName {
    private String value;

    public TodoName(String value) throws Exception {
        this.setValue(value);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) throws Exception {
        if (value == null) {
            throw new InvalidTodoNameException("Todo name value must not be null.");
        }

        this.value = value.trim();

        if (this.value.length() < 3 || this.value.length() > 64) {
            throw new InvalidTodoNameException("Todo name must be between 3 to 64 characters long.");
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
