package br.com.rafaeltmbr.todolist.common.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.common.core.exceptions.DomainException;
import br.com.rafaeltmbr.todolist.common.infra.presentation.http.entities.ErrorResponseBody;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.NameAlreadyUsedException;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    private final Map<Class<?>, HttpStatus> statusMap = Map.of(
            TodoNotFoundException.class, HttpStatus.NOT_FOUND,
            NameAlreadyUsedException.class, HttpStatus.BAD_REQUEST
    );

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponseBody> handleDomainException(DomainException exception) {
        HttpStatus status = statusMap.get(exception.getClass());
        if (status == null) {
            status = HttpStatus.BAD_REQUEST;
        }

        var errorResponse = new ErrorResponseBody(exception.getMessage());

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseBody handleException(Exception exception) {
        return new ErrorResponseBody(exception.getMessage());
    }
}
