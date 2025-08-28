package br.com.rafaeltmbr.todolist.user.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.user.core.entities.Email;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.core.entities.UserName;
import br.com.rafaeltmbr.todolist.user.core.entities.UserPassword;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import br.com.rafaeltmbr.todolist.user.core.use_cases.CreateUserAccountUseCase;
import br.com.rafaeltmbr.todolist.user.core.use_cases.CreateUserSessionUseCase;
import br.com.rafaeltmbr.todolist.user.infra.data.repositories.IUserRepositoryJpa;
import br.com.rafaeltmbr.todolist.user.infra.di.UserContainer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserContainer userContainer;

    @Autowired
    private IUserRepositoryJpa userRepositoryJpaInterface;

    @PostConstruct
    public void init() throws UserException {
        userContainer = UserContainer.getInstance(userRepositoryJpaInterface);
    }

    @PostMapping("/session")
    public UserSessionResponse userSession(@RequestBody UserSessionRequestBody body) throws Exception {
        var params = new CreateUserSessionUseCase.Params(
                new Email(body.email),
                new UserPassword(body.password)
        );

        CreateUserSessionUseCase.Response response = userContainer.useCases.createUserSession().execute(params);
        User user = response.user();

        return new UserSessionResponse(
                response.token().getValue(),
                new UserResponseBody(
                        user.getId().getValue(),
                        user.getName().getValue(),
                        user.getEmail().getValue(),
                        user.getCreatedAt().toString()
                )
        );
    }

    @PostMapping("/account")
    public UserResponseBody createUser(@RequestBody CreateUserRequestBody body) throws Exception {
        var params = new CreateUserAccountUseCase.Params(
                new UserName(body.name),
                new Email(body.email),
                new UserPassword(body.password)
        );

        var user = userContainer.useCases.createUserAccount().execute(params);

        return new UserResponseBody(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getCreatedAt().toString()
        );
    }

    public record UserSessionRequestBody(
            String email,
            String password
    ) {
    }

    public record UserSessionResponse(
            String token,
            UserResponseBody user
    ) {
    }

    public record CreateUserRequestBody(
            String name,
            String email,
            String password
    ) {
    }

    public record UserResponseBody(
            String id,
            String name,
            String email,
            String created_at
    ) {
    }
}
