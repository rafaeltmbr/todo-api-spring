package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

public class UserPasswordHash {
    private String value;

    public UserPasswordHash(String value) throws UserException {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws UserException {
        if (value == null) {
            throw new UserException(UserException.Type.USER_INVALID_PASSWORD_HASH, "User password hash value must not be null.");
        }

        this.value = value.trim();
        var length = 32;

        if (this.value.length() == 32) {
            throw new UserException(UserException.Type.USER_INVALID_PASSWORD_HASH, "User password hash must be " + length + " characters long.");
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

        var other = (UserPasswordHash) obj;

        return value.equals(other.value);
    }
}
