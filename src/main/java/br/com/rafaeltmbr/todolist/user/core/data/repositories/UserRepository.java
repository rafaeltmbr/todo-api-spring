package br.com.rafaeltmbr.todolist.user.core.data.repositories;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.dtos.CreateUserDto;
import br.com.rafaeltmbr.todolist.user.core.entities.Email;
import br.com.rafaeltmbr.todolist.user.core.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll() throws Exception;

    Optional<User> findById(Id id) throws Exception;

    Optional<User> findByEmail(Email email) throws Exception;

    User create(CreateUserDto dto) throws Exception;

    void update(User user) throws Exception;

    void delete(Id id) throws Exception;
}
