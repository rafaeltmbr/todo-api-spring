package br.com.rafaeltmbr.todolist.todo.core.dtos;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;

public record UpdateTodoDto(
        Id id,
        TodoName name,
        boolean done
) {
}
