package br.com.rafaeltmbr.todolist.infra.di;

import br.com.rafaeltmbr.todolist.core.data.repositories.TodoRepository;

public record AppRepositoryContainer(
        TodoRepository todo
) {
}
