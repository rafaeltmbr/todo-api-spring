package br.com.rafaeltmbr.todolist.infra.presentation.http.entities;

public record UpdateTodoRequestBody(
        String name,
        boolean done
) {
}
