package br.com.rafaeltmbr.todolist.user.core.use_cases;

import br.com.rafaeltmbr.todolist.user.core.data.repositories.UserRepository;
import br.com.rafaeltmbr.todolist.user.core.entities.Email;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;
import br.com.rafaeltmbr.todolist.user.core.entities.UserPassword;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import br.com.rafaeltmbr.todolist.user.core.services.UserAuthenticationTokenService;
import br.com.rafaeltmbr.todolist.user.core.services.UserPasswordHashService;

import java.util.Optional;

public class CreateUserSessionUseCase {
    private final UserRepository userRepository;
    private final UserPasswordHashService userPasswordHashService;
    private final UserAuthenticationTokenService userAuthenticationTokenService;

    public CreateUserSessionUseCase(
            UserRepository userRepository,
            UserPasswordHashService userPasswordHashService,
            UserAuthenticationTokenService userAuthenticationTokenService
    ) {
        this.userRepository = userRepository;
        this.userPasswordHashService = userPasswordHashService;
        this.userAuthenticationTokenService = userAuthenticationTokenService;
    }

    public Response execute(Params params) throws Exception {
        Email email = params.email();
        Optional<User> foundByEmail = userRepository.findByEmail(email);
        if (foundByEmail.isEmpty()) {
            throw new UserException(UserException.Type.USER_NOT_FOUND, "User with email '" + email + "' not found.");
        }

        User user = foundByEmail.get();

        if (!userPasswordHashService.checkMatch(params.password(), user.getPasswordHash())) {
            throw new UserException(UserException.Type.USER_INVALID_CREDENTIALS, "Invalid credentials.");
        }

        UserAuthenticationToken token = userAuthenticationTokenService.generateToken(user.getId());

        return new Response(user, token);
    }

    public record Params(Email email, UserPassword password) {
    }

    public record Response(User user, UserAuthenticationToken token) {
    }
}
