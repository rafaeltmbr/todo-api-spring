package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

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
            throw new UserException(UserException.Type.USER_INVALID_PASSWORD, "User password value must not be null.");
        }

        this.value = value.trim();
        var minimum = 8;
        var maximum = 32;

        if (this.value.length() < minimum || this.value.length() > maximum) {
            throw new UserException(UserException.Type.USER_INVALID_PASSWORD, "User password must be between " + minimum + " to " + maximum + " characters long.");
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
