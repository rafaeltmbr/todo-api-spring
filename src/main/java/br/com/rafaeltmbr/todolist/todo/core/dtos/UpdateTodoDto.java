package br.com.rafaeltmbr.todolist.todo.core.dtos;

import java.util.UUID;

public record UpdateTodoDto(
        UUID id,
        String nameValue,
        boolean done
) {
}
