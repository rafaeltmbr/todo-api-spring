package br.com.rafaeltmbr.todolist.user.core.data.repositories;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.entities.Email;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.core.entities.UserName;
import br.com.rafaeltmbr.todolist.user.core.entities.UserPasswordHash;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll() throws Exception;

    Optional<User> findById(Id id) throws Exception;

    Optional<User> findByEmail(Email email) throws Exception;

    User create(CreateParams params) throws Exception;

    User update(UpdateParams params) throws Exception;

    void delete(Id id) throws Exception;

    record CreateParams(
            UserName name,
            Email email,
            UserPasswordHash passwordHash
    ) {
    }

    record UpdateParams(
            Id id,
            UserName name,
            UserPasswordHash passwordHash
    ) {
    }
}
