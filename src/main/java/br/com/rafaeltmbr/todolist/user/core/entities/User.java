package br.com.rafaeltmbr.todolist.core.entities;

import java.util.UUID;

public record User(
        UUID id,
        UserName name,
        String email
) {
}
