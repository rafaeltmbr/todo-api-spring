package br.com.rafaeltmbr.todolist.common.core.entities;

import br.com.rafaeltmbr.todolist.common.core.exceptions.CommonException;

import java.util.UUID;

public class Id {
    private UUID value;

    public Id() {
        value = UUID.randomUUID();
    }

    public Id(String value) throws CommonException {
        setValue(value);
    }

    public String getValue() {
        return value.toString();
    }

    public void setValue(String value) throws CommonException {
        if (value == null) {
            throw new CommonException(CommonException.Type.COMMON_INVALID_ID, "Id value should not be null.");
        }

        try {
            this.value = UUID.fromString(value);
        } catch (IllegalArgumentException exception) {
            throw new CommonException(CommonException.Type.COMMON_INVALID_ID, "Invalid id.");
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        var other = (Id) obj;

        return value.equals(other.value);
    }
}
