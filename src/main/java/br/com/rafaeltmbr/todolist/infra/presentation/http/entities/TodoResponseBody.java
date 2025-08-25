package br.com.rafaeltmbr.todolist.infra.presentation.http.entities;

import br.com.rafaeltmbr.todolist.core.entities.Todo;

public record TodoResponseBody(
        String id,
        String name,
        boolean done
) {
    public TodoResponseBody(Todo todo) {
        this(
                todo.id().toString(),
                todo.name().getValue(),
                todo.done()
        );
    }
}
