package br.com.rafaeltmbr.todolist.core.entities;

public class Email {
    private String value;

    public Email(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value == null) {

        }

        var formatted = value.trim();
    }
}
