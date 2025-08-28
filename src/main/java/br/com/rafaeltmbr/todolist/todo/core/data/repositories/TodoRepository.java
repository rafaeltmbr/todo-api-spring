package br.com.rafaeltmbr.todolist.todo.core.data.repositories;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;

import java.util.List;
import java.util.Optional;


public interface TodoRepository {
    List<Todo> listAll(ListAllParams params) throws Exception;

    Optional<Todo> findById(FindByIdParams params) throws Exception;

    Optional<Todo> findByName(FindByNameParams params) throws Exception;

    Todo create(CreateParams params) throws Exception;

    Todo update(UpdateParams params) throws Exception;

    void delete(DeleteParams params) throws Exception;

    record CreateParams(
            Id userId,
            TodoName name
    ) {
    }

    record UpdateParams(
            Id userId,
            Id todoId,
            TodoName name,
            boolean done
    ) {
    }

    record ListAllParams(
            Id userId
    ) {
    }

    record FindByIdParams(
            Id userId,
            Id todoId
    ) {
    }

    record FindByNameParams(
            Id userId,
            TodoName name
    ) {
    }

    record DeleteParams(
            Id userId,
            Id todoId
    ) {
    }
}
