package br.com.rafaeltmbr.todolist.user.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.data.repositories.UserRepository;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import br.com.rafaeltmbr.todolist.user.core.services.UserAuthenticationTokenService;

import java.util.Optional;

public class AuthenticateUserUseCase {
    private final UserAuthenticationTokenService userAuthenticationTokenService;
    private final UserRepository userRepository;

    public AuthenticateUserUseCase(
            UserAuthenticationTokenService userAuthenticationTokenService,
            UserRepository userRepository
    ) {
        this.userAuthenticationTokenService = userAuthenticationTokenService;
        this.userRepository = userRepository;
    }

    public User execute(UserAuthenticationToken token) throws Exception {
        Id id = userAuthenticationTokenService.validate(token);
        Optional<User> found = userRepository.findById(id);
        if (found.isEmpty()) {
            throw new UserException(UserException.Type.USER_NOT_FOUND, "User not found.");
        }

        return found.get();
    }
}
