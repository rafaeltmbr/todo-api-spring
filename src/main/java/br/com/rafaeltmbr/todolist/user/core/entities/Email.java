package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

public class Email {
    private String value;

    public Email(String value) throws Exception {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws Exception {
        if (value == null) {
            throw new UserException(UserException.Type.USER_INVALID_EMAIL, "Email value must not be null.");
        }

        this.value = value.trim();

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(this.value);

        if (!m.matches()) {
            throw new UserException(UserException.Type.USER_INVALID_EMAIL, "Invalid email format.");
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

        var other = (Email) obj;

        return value.equals(other.value);
    }
}
