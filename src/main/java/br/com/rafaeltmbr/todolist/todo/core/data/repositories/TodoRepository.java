package br.com.rafaeltmbr.todolist.todo.core.data.repositories;

import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TodoRepository {
    List<Todo> listAll() throws Exception;

    Optional<Todo> findById(UUID id) throws Exception;

    Optional<Todo> findByName(TodoName name) throws Exception;

    Todo create(TodoName name) throws Exception;

    void update(Todo todo) throws Exception;

    void delete(UUID id) throws Exception;
}
