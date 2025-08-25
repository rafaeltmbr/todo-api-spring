package br.com.rafaeltmbr.todolist.infra.data.repositories.jpa;

import br.com.rafaeltmbr.todolist.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.core.entities.Name;
import br.com.rafaeltmbr.todolist.core.entities.Todo;
import br.com.rafaeltmbr.todolist.core.exceptions.DomainException;

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
    public Optional<Todo> findByName(Name name) throws Exception {
        Optional<TodoEntityJpa> todo = repositoryJpaInterface.findByName(name.getValue());
        if (todo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(todo.get().toTodo());
    }

    @Override
    public Todo create(Name name) throws Exception {
        var entity = new TodoEntityJpa(
                null,
                name.getValue(),
                false,
                LocalDateTime.now()
        );

        TodoEntityJpa created = repositoryJpaInterface.save(entity);

        return new Todo(created.id, name, false);
    }

    @Override
    public void update(Todo todo) throws Exception {
        Optional<TodoEntityJpa> found = repositoryJpaInterface.findById(todo.id());
        if (found.isEmpty()) {
            throw new DomainException("Todo not found.");
        }

        var entity = new TodoEntityJpa(
                todo.id(),
                todo.name().getValue(),
                todo.done(),
                found.get().createdAt
        );

        repositoryJpaInterface.save(entity);
    }

    @Override
    public void delete(UUID id) throws Exception {
        repositoryJpaInterface.deleteById(id);
    }
}
