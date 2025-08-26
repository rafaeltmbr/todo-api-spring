package br.com.rafaeltmbr.todolist.user.core.use_cases;

import br.com.rafaeltmbr.todolist.user.core.data.repositories.UserRepository;
import br.com.rafaeltmbr.todolist.user.core.entities.*;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import br.com.rafaeltmbr.todolist.user.core.services.UserPasswordHashService;

import java.util.Optional;

public class CreateUserAccountUseCase {
    private final UserRepository userRepository;
    private final UserPasswordHashService userPasswordHashService;

    public CreateUserAccountUseCase(UserRepository userRepository, UserPasswordHashService userPasswordHashService) {
        this.userRepository = userRepository;
        this.userPasswordHashService = userPasswordHashService;
    }

    public User execute(Params params) throws Exception {
        Optional<User> foundByEmail = userRepository.findByEmail(params.email());
        if (foundByEmail.isPresent()) {
            throw new UserException(UserException.Type.USER_EMAIL_ALREADY_USED, "Email address already in use.");
        }

        UserPasswordHash passwordHash = userPasswordHashService.generateHash(params.password());

        var createParams = new UserRepository.CreateParams(params.name(), params.email(), passwordHash);
        return userRepository.create(createParams);
    }

    public record Params(UserName name, Email email, UserPassword password) {
    }
}
