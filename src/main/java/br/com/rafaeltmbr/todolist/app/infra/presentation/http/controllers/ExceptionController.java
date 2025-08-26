package br.com.rafaeltmbr.todolist.app.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.app.infra.presentation.http.entities.ErrorResponseBody;
import br.com.rafaeltmbr.todolist.common.core.exceptions.CommonException;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(TodoException.class)
    public ResponseEntity<ErrorResponseBody> handleTodoException(TodoException exception) {
        var status = switch (exception.getType()) {
            case TODO_INVALID_NAME,
                 TODO_INVALID_STATE,
                 TODO_NAME_ALREADY_USED -> HttpStatus.BAD_REQUEST;

            case TODO_NOT_FOUND -> HttpStatus.NOT_FOUND;
        };

        String error = exception.getType().toString().toLowerCase();
        String message = exception.getMessage();
        var errorResponse = new ErrorResponseBody(error, message);

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponseBody> handleUserException(UserException exception) {
        var status = switch (exception.getType()) {
            case USER_INVALID_NAME,
                 USER_INVALID_STATE,
                 USER_INVALID_PASSWORD,
                 USER_INVALID_EMAIL -> HttpStatus.BAD_REQUEST;

            case USER_NOT_FOUND -> HttpStatus.NOT_FOUND;

            case USER_INVALID_PASSWORD_HASH -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        String error = exception.getType().toString().toLowerCase();
        String message = exception.getMessage();
        var errorResponse = new ErrorResponseBody(error, message);

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponseBody> handleCommonException(CommonException exception) {
        var status = switch (exception.getType()) {
            case COMMON_INVALID_CREATED_AT -> HttpStatus.INTERNAL_SERVER_ERROR;
            case COMMON_INVALID_ID -> HttpStatus.BAD_REQUEST;
        };

        String error = exception.getType().toString().toLowerCase();
        String message = exception.getMessage();
        var errorResponse = new ErrorResponseBody(error, message);

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseBody handleUnexpectedException(Exception exception) {
        String error = "server_internal_error";
        String message = exception.getMessage();

        return new ErrorResponseBody(error, message);
    }
}
