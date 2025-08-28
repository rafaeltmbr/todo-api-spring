package br.com.rafaeltmbr.todolist.user.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;
import br.com.rafaeltmbr.todolist.user.core.services.UserAuthenticationTokenService;

public class AuthenticateUserUseCase {
    private final UserAuthenticationTokenService userAuthenticationTokenService;

    public AuthenticateUserUseCase(
            UserAuthenticationTokenService userAuthenticationTokenService
    ) {
        this.userAuthenticationTokenService = userAuthenticationTokenService;
    }

    public Id execute(UserAuthenticationToken token) throws Exception {
        return userAuthenticationTokenService.validate(token);
    }
}
