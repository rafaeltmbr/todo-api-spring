package br.com.rafaeltmbr.todolist.common.core.entities;

import br.com.rafaeltmbr.todolist.common.core.exceptions.CommonException;

import java.time.LocalDateTime;

public class CreatedAt {
    private LocalDateTime value;

    public CreatedAt() {
        value = LocalDateTime.now();
    }

    public CreatedAt(LocalDateTime value) throws Exception {
        setValue(value);
    }

    public LocalDateTime getValue() {
        return value;
    }

    public void setValue(LocalDateTime value) throws Exception {
        if (value == null) {
            throw new CommonException(CommonException.Type.COMMON_INVALID_CREATED_AT, "CreatedAt value should not be null.");
        }

        if (value.isAfter(LocalDateTime.now())) {
            throw new CommonException(CommonException.Type.COMMON_INVALID_CREATED_AT, "CreatedAt should not be a time in the future.");
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        var other = (CreatedAt) obj;

        return value.equals(other.value);
    }
}
