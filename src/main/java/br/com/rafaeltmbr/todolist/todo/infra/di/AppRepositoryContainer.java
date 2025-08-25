package br.com.rafaeltmbr.todolist.todo.infra.di;

import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;

public record AppRepositoryContainer(
        TodoRepository todo
) {
}
