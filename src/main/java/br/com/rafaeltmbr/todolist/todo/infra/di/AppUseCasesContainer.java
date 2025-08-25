package br.com.rafaeltmbr.todolist.todo.infra.di;

import br.com.rafaeltmbr.todolist.todo.core.use_cases.CreateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.DeleteTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.ListTodosUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.UpdateTodoUseCase;

public record AppUseCasesContainer(
        ListTodosUseCase listTodos,
        CreateTodoUseCase createTodo,
        UpdateTodoUseCase updateTodo,
        DeleteTodoUseCase deleteTodo
) {
}
