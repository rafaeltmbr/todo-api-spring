package br.com.rafaeltmbr.todolist.core.entities;

import java.util.UUID;

public record Todo(
        UUID id,
        Name name,
        boolean done
) {
}
