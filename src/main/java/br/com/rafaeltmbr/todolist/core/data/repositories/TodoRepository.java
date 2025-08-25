package br.com.rafaeltmbr.todolist.core.data.repositories;

import br.com.rafaeltmbr.todolist.core.entities.Name;
import br.com.rafaeltmbr.todolist.core.entities.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TodoRepository {
    List<Todo> listAll() throws Exception;

    Optional<Todo> findById(UUID id) throws Exception;

    Optional<Todo> findByName(Name name) throws Exception;

    Todo create(Name name) throws Exception;

    void update(Todo todo) throws Exception;

    void delete(UUID id) throws Exception;
}
