package br.com.rafaeltmbr.todolist.app.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.app.infra.presentation.http.entities.ErrorResponseBody;
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
            case TodoException.Type.TODO_INVALID_NAME,
                 TodoException.Type.TODO_INVALID_STATE,
                 TodoException.Type.TODO_NAME_ALREADY_USED -> HttpStatus.BAD_REQUEST;
            case TodoException.Type.TODO_NOT_FOUND -> HttpStatus.NOT_FOUND;
        };

        String error = exception.getType().toString().toLowerCase();
        String message = exception.getMessage();
        var errorResponse = new ErrorResponseBody(error, message);

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponseBody> handleUserException(UserException exception) {
        var status = switch (exception.getType()) {
            case UserException.Type.USER_INVALID_NAME,
                 UserException.Type.USER_INVALID_STATE,
                 UserException.Type.USER_INVALID_PASSWORD,
                 UserException.Type.USER_INVALID_PASSWORD_HASH,
                 UserException.Type.USER_INVALID_EMAIL -> HttpStatus.BAD_REQUEST;

            case UserException.Type.USER_NOT_FOUND -> HttpStatus.NOT_FOUND;
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
