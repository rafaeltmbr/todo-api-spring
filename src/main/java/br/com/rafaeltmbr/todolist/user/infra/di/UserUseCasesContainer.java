package br.com.rafaeltmbr.todolist.user.infra.di;

import br.com.rafaeltmbr.todolist.user.core.use_cases.CreateUserAccountUseCase;
import br.com.rafaeltmbr.todolist.user.core.use_cases.CreateUserSessionUseCase;

public record UserUseCasesContainer(
        CreateUserAccountUseCase createUserAccount,
        CreateUserSessionUseCase createUserSession
) {
}
