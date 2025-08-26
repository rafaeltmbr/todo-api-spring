package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

public class UserName {
    private String value;

    UserName(String value) throws UserException {
        setValue(value);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) throws UserException {
        if (value == null) {
            throw new UserException(UserException.Type.USER_INVALID_NAME, "User name value must not be null.");
        }

        this.value = value.trim();
        var minimum = 3;
        var maximum = 64;

        if (this.value.length() < minimum || this.value.length() > maximum) {
            throw new UserException(UserException.Type.USER_INVALID_NAME, "User name must be between " + minimum + " to " + maximum + " characters long.");
        }
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() != getClass()) return false;

        var other = (UserName) obj;

        return value.equals(other.value);
    }
}
