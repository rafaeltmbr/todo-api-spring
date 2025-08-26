package br.com.rafaeltmbr.todolist.user.infra.di;

import br.com.rafaeltmbr.todolist.user.core.services.UserAuthenticationTokenService;
import br.com.rafaeltmbr.todolist.user.core.services.UserPasswordHashService;

public record UserServicesContainer(
        UserAuthenticationTokenService authenticationToken,
        UserPasswordHashService passwordHash
) {
}
