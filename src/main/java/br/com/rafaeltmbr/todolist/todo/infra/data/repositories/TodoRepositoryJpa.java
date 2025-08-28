package br.com.rafaeltmbr.todolist.todo.infra.data.repositories;

import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public record TodoRepositoryJpa(ITodoRepositoryJpa repositoryJpaInterface) implements TodoRepository {
    @Override
    public List<Todo> listAll(TodoRepository.ListAllParams params) throws Exception {
        ArrayList<Todo> todos = new ArrayList<>();

        var userId = UUID.fromString(params.userId().toString());
        for (TodoEntityJpa todo : repositoryJpaInterface.findAllByUserId(userId)) {
            todos.add(todo.toTodo());
        }

        return todos;
    }

    @Override
    public Optional<Todo> findById(TodoRepository.FindByIdParams params) throws Exception {
        var todoId = UUID.fromString(params.todoId().toString());
        var userId = UUID.fromString(params.userId().toString());
        Optional<TodoEntityJpa> todo = repositoryJpaInterface.findByUserIdAndId(userId, todoId);
        if (todo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(todo.get().toTodo());
    }

    @Override
    public Optional<Todo> findByName(TodoRepository.FindByNameParams params) throws Exception {
        var userId = UUID.fromString(params.userId().toString());
        String name = params.name().toString();
        Optional<TodoEntityJpa> todo = repositoryJpaInterface.findByUserIdAndName(userId, name);
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
                UUID.fromString(params.userId().toString()),
                null
        );

        TodoEntityJpa created = repositoryJpaInterface.save(entity);

        return created.toTodo();
    }

    @Override
    public Todo update(UpdateParams params) throws Exception {
        var userId = UUID.fromString(params.userId().toString());
        var todoId = UUID.fromString(params.todoId().toString());
        Optional<TodoEntityJpa> found = repositoryJpaInterface.findByUserIdAndId(userId, todoId);
        if (found.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + params.todoId() + "' not found.");
        }

        var entity = new TodoEntityJpa(
                todoId,
                params.name().getValue(),
                params.done(),
                userId,
                found.get().createdAt
        );

        return repositoryJpaInterface.save(entity).toTodo();
    }

    @Override
    public void delete(TodoRepository.DeleteParams params) throws Exception {
        var todoId = UUID.fromString(params.todoId().toString());
        var userId = UUID.fromString(params.userId().toString());
        repositoryJpaInterface.deleteByUserIdAndId(userId, todoId);
    }
}
