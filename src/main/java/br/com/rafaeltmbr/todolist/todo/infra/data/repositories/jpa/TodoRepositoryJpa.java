package br.com.rafaeltmbr.todolist.todo.infra.data.repositories.jpa;

import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.dtos.CreateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.time.LocalDateTime;
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
    public Optional<Todo> findById(UUID id) throws Exception {
        Optional<TodoEntityJpa> todo = repositoryJpaInterface.findById(id);
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
    public Todo create(CreateTodoDto dto) throws Exception {
        TodoName name = dto.name();
        var entity = new TodoEntityJpa(
                null,
                name.getValue(),
                false,
                LocalDateTime.now()
        );

        TodoEntityJpa created = repositoryJpaInterface.save(entity);

        return created.toTodo();
    }

    @Override
    public void update(Todo todo) throws Exception {
        UUID id = todo.getId();
        Optional<TodoEntityJpa> found = repositoryJpaInterface.findById(id);
        if (found.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + id + "' not found.");
        }

        var entity = new TodoEntityJpa(
                todo.getId(),
                todo.getName().getValue(),
                todo.getDone(),
                found.get().createdAt
        );

        repositoryJpaInterface.save(entity);
    }

    @Override
    public void delete(UUID id) throws Exception {
        repositoryJpaInterface.deleteById(id);
    }
}
