package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.user.core.exceptions.InvalidUserPasswordException;

public class UserPassword {
    private String value;

    public UserPassword(String value) throws Exception {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws Exception {
        if (value == null) {
            throw new InvalidUserPasswordException("User password value must not be null.");
        }

        this.value = value.trim();

        if (this.value.length() < 8 || this.value.length() > 32) {
            throw new InvalidUserPasswordException("User password must be between 8 to 32 characters longs.");
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() != getClass()) return false;

        var other = (UserPassword) obj;

        return value.equals(other.value);
    }
}
