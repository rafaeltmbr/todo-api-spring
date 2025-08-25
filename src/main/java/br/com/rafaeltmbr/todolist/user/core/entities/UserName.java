package br.com.rafaeltmbr.todolist.core.entities;

import br.com.rafaeltmbr.todolist.core.exceptions.InvalidUserNameException;

public class UserName {
    private String value;

    UserName(String name) throws Exception {
        this.value = name.trim();

        validate();
    }

    private void validate() throws Exception {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws Exception {
        if (value == null) {

        }

        var formatted = value.trim();

        if (value.length() < 3 || value.length() > 64) {
            throw new InvalidUserNameException();
        }

        this.value = formatted;
    }
}
