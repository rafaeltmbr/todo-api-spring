package br.com.rafaeltmbr.todolist.user.infra.data.repositories;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.data.repositories.UserRepository;
import br.com.rafaeltmbr.todolist.user.core.entities.Email;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryJpa implements UserRepository {
    private final IUserRepositoryJpa repositoryJpaInterface;

    public UserRepositoryJpa(IUserRepositoryJpa repositoryJpaInterface) {
        this.repositoryJpaInterface = repositoryJpaInterface;
    }

    @Override
    public List<User> findAll() throws Exception {
        var list = new ArrayList<User>();
        for (var userEntity : repositoryJpaInterface.findAll()) {
            list.add(userEntity.toUser());
        }
        return list;
    }

    @Override
    public Optional<User> findById(Id id) throws Exception {
        Optional<UserEntityJpa> found = repositoryJpaInterface.findById(UUID.fromString(id.toString()));
        return found.isPresent() ? Optional.of(found.get().toUser()) : Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(Email email) throws Exception {
        Optional<UserEntityJpa> found = repositoryJpaInterface.findByEmail(email.toString());
        return found.isPresent() ? Optional.of(found.get().toUser()) : Optional.empty();
    }

    @Override
    public User create(CreateParams params) throws Exception {
        var entity = new UserEntityJpa(
                null,
                params.name().toString(),
                params.email().toString(),
                params.passwordHash().toString(),
                null
        );

        UserEntityJpa created = repositoryJpaInterface.save(entity);
        return created.toUser();
    }

    @Override
    public User update(UpdateParams params) throws Exception {
        var uuid = UUID.fromString(params.id().toString());
        Optional<UserEntityJpa> found = repositoryJpaInterface.findById(uuid);
        if (found.isEmpty()) {
            throw new UserException(UserException.Type.USER_NOT_FOUND, "User with id '" + uuid + "' not found.");
        }

        var entity = new UserEntityJpa(
                uuid,
                params.name().getValue(),
                found.get().email,
                params.passwordHash().getValue(),
                found.get().createdAt
        );

        return repositoryJpaInterface.save(entity).toUser();
    }

    @Override
    public void delete(Id id) throws Exception {
        repositoryJpaInterface.deleteById(UUID.fromString(id.toString()));
    }
}
