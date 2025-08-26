package br.com.rafaeltmbr.todolist.todo.core.data.repositories;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;

import java.util.List;
import java.util.Optional;


public interface TodoRepository {
    List<Todo> listAll() throws Exception;

    Optional<Todo> findById(Id id) throws Exception;

    Optional<Todo> findByName(TodoName name) throws Exception;

    Todo create(CreateParams params) throws Exception;

    Todo update(UpdateParams params) throws Exception;

    void delete(Id id) throws Exception;

    record CreateParams(TodoName name) {
    }

    record UpdateParams(
            Id id,
            TodoName name,
            boolean done
    ) {
    }
}
