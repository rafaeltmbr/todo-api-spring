package br.com.rafaeltmbr.todolist.app.infra.presentation.http.entities;

public record ErrorResponseBody(
        String error,
        String message
) {
}
