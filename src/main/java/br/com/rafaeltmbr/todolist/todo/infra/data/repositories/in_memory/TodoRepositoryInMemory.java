package br.com.rafaeltmbr.todolist.todo.infra.data.repositories.in_memory;

import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.NameAlreadyUsedException;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoRepositoryInMemory implements TodoRepository {
    private final ArrayList<Todo> todos = new ArrayList<>();

    @Override
    public List<Todo> listAll() throws Exception {
        return this.todos;
    }

    @Override
    public Optional<Todo> findById(UUID id) throws Exception {
        for (Todo todo : this.todos) {
            if (todo.getId().equals(id)) return Optional.of(todo);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Todo> findByName(TodoName name) throws Exception {
        for (Todo todo : this.todos) {
            if (todo.getName().equals(name)) return Optional.of(todo);
        }

        return Optional.empty();
    }

    @Override
    public Todo create(TodoName name) throws Exception {
        Optional<Todo> found = this.findByName(name);
        if (found.isPresent()) {
            throw new NameAlreadyUsedException();
        }

        var todo = new Todo(UUID.randomUUID(), name, false);
        this.todos.add(todo);
        return todo;
    }

    @Override
    public void update(Todo todo) throws Exception {
        Optional<Todo> foundById = this.findById(todo.getId());
        if (foundById.isEmpty()) {
            throw new TodoNotFoundException();
        }

        Optional<Todo> foundByName = this.findByName(todo.getName());
        if (foundByName.isPresent() && !foundByName.get().getId().equals(todo.getId())) {
            throw new NameAlreadyUsedException();
        }

        this.todos.remove(foundById.get());
        this.todos.add(todo);
    }

    @Override
    public void delete(UUID id) throws Exception {
        Optional<Todo> found = this.findById(id);
        if (found.isEmpty()) {
            throw new TodoNotFoundException();
        }

        this.todos.remove(found.get());
    }
}
