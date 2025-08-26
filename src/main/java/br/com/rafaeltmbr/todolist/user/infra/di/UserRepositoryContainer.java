package br.com.rafaeltmbr.todolist.user.infra.di;

import br.com.rafaeltmbr.todolist.user.core.data.repositories.UserRepository;

public record UserRepositoryContainer(
        UserRepository user
) {
}
