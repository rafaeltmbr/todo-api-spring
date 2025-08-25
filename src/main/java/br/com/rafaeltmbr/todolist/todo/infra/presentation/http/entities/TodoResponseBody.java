package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities;

import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;

public record TodoResponseBody(
        String id,
        String name,
        boolean done
) {
    public TodoResponseBody(Todo todo) {
        this(
                todo.getId().toString(),
                todo.getName().getValue(),
                todo.getDone()
        );
    }
}
