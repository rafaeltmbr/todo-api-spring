package br.com.rafaeltmbr.todolist.todo.core.dtos;

import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;

import java.util.UUID;

public record UpdateTodoDto(
        UUID id,
        TodoName name,
        boolean done
) {
}
