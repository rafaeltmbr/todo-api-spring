package br.com.rafaeltmbr.todolist.user.infra.di;

import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import br.com.rafaeltmbr.todolist.user.core.use_cases.AuthenticateUserUseCase;
import br.com.rafaeltmbr.todolist.user.core.use_cases.CreateUserAccountUseCase;
import br.com.rafaeltmbr.todolist.user.core.use_cases.CreateUserSessionUseCase;
import br.com.rafaeltmbr.todolist.user.infra.data.repositories.IUserRepositoryJpa;
import br.com.rafaeltmbr.todolist.user.infra.data.repositories.UserRepositoryJpa;
import br.com.rafaeltmbr.todolist.user.infra.services.UserAuthenticationTokenServiceJwt;
import br.com.rafaeltmbr.todolist.user.infra.services.UserPasswordHashServiceBcrypt;

public class UserContainer {
    private static UserContainer container;

    public UserUseCasesContainer useCases;
    public UserDataContainer data;

    private UserContainer() {
    }

    public static synchronized UserContainer getInstance() throws UserException {
        return UserContainer.getInstance(null);
    }

    public static synchronized UserContainer getInstance(IUserRepositoryJpa userRepositoryJpaInterface) throws UserException {
        if (container != null) return container;

        if (userRepositoryJpaInterface == null) {
            throw new UserException(UserException.Type.USER_MISSING_DEPENDENCIES, "Missing userRepositoryJpaInterface.");
        }

        container = new UserContainer();

        var userRepository = new UserRepositoryJpa(userRepositoryJpaInterface);

        var services = new UserServicesContainer(
                new UserAuthenticationTokenServiceJwt(),
                new UserPasswordHashServiceBcrypt()
        );

        container.data = new UserDataContainer(new UserRepositoryContainer(userRepository));

        container.useCases = new UserUseCasesContainer(
                new AuthenticateUserUseCase(services.authenticationToken()),
                new CreateUserAccountUseCase(userRepository, services.passwordHash()),
                new CreateUserSessionUseCase(userRepository, services.passwordHash(), services.authenticationToken())
        );

        return container;
    }
}
