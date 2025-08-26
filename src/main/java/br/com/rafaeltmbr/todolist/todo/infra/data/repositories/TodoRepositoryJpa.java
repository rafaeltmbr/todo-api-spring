package br.com.rafaeltmbr.todolist.todo.infra.data.repositories;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public record TodoRepositoryJpa(ITodoRepositoryJpa repositoryJpaInterface) implements TodoRepository {
    @Override
    public List<Todo> listAll() throws Exception {
        ArrayList<Todo> todos = new ArrayList<>();

        for (TodoEntityJpa todo : repositoryJpaInterface.findAll()) {
            todos.add(todo.toTodo());
        }

        return todos;
    }

    @Override
    public Optional<Todo> findById(Id id) throws Exception {
        Optional<TodoEntityJpa> todo = repositoryJpaInterface.findById(UUID.fromString(id.toString()));
        if (todo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(todo.get().toTodo());
    }

    @Override
    public Optional<Todo> findByName(TodoName name) throws Exception {
        Optional<TodoEntityJpa> todo = repositoryJpaInterface.findByName(name.getValue());
        if (todo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(todo.get().toTodo());
    }

    @Override
    public Todo create(CreateParams params) throws Exception {
        var entity = new TodoEntityJpa(
                null,
                params.name().toString(),
                false,
                null
        );

        TodoEntityJpa created = repositoryJpaInterface.save(entity);

        return created.toTodo();
    }

    @Override
    public Todo update(UpdateParams params) throws Exception {
        var uuid = UUID.fromString(params.id().toString());
        Optional<TodoEntityJpa> found = repositoryJpaInterface.findById(uuid);
        if (found.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + uuid + "' not found.");
        }

        var entity = new TodoEntityJpa(
                uuid,
                params.name().getValue(),
                params.done(),
                found.get().createdAt
        );

        return repositoryJpaInterface.save(entity).toTodo();
    }

    @Override
    public void delete(Id id) throws Exception {
        repositoryJpaInterface.deleteById(UUID.fromString(id.toString()));
    }
}
