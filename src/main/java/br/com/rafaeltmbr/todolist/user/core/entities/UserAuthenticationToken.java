package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

public class UserAuthenticationToken {
    private String value;

    public UserAuthenticationToken(String value) throws UserException {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws UserException {
        if (value == null) {
            throw new UserException(UserException.Type.USER_INVALID_AUTHENTICATION_TOKEN, "Authentication token value must not be null.");
        }

        this.value = value.trim();

        if (this.value.isEmpty()) {
            throw new UserException(UserException.Type.USER_INVALID_AUTHENTICATION_TOKEN, "Invalid authentication token.");
        }
    }
}
