package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities;

public record UpdateTodoRequestBody(
        String name,
        boolean done
) {
}
