package br.com.rafaeltmbr.todolist.core.entities;

public class Name {
    private String value;

    public Name(String value) throws Exception {
        this.setValue(value);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) throws Exception {
        if (value == null) {
            throw new Exception("Value must not be null.");
        }

        String formatted = value.trim();

        if (formatted.isEmpty()) {
            throw new Exception("Value must not be empty.");
        }

        this.value = formatted;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        var other = (Name) obj;

        return this.value.equals(other.value);
    }
}
