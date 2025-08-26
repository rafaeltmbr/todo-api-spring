package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities;

import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;

public record TodoResponseBody(
        String id,
        String name,
        boolean done,
        String created_at
) {
    public TodoResponseBody(Todo todo) {
        this(
                todo.getId().toString(),
                todo.getName().getValue(),
                todo.getDone(),
                todo.getCreatedAt().toString()
        );
    }
}
