package br.com.rafaeltmbr.todolist.todo.infra.data.repositories.in_memory;

import br.com.rafaeltmbr.todolist.common.core.entities.CreatedAt;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.dtos.CreateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

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
    public Todo create(CreateTodoDto dto) throws Exception {
        TodoName name = dto.name();
        Optional<Todo> found = this.findByName(name);
        if (found.isPresent()) {
            throw new TodoException(TodoException.Type.TODO_NAME_ALREADY_USED, "Todo name '" + name + "' already in use.");
        }

        var todo = new Todo(UUID.randomUUID(), name, false, new CreatedAt());
        this.todos.add(todo);
        return todo;
    }

    @Override
    public void update(Todo todo) throws Exception {
        UUID id = todo.getId();
        Optional<Todo> foundById = this.findById(id);
        if (foundById.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + id + "' not found.");
        }

        TodoName name = todo.getName();
        Optional<Todo> foundByName = this.findByName(name);
        if (foundByName.isPresent() && !foundByName.get().getId().equals(todo.getId())) {
            throw new TodoException(TodoException.Type.TODO_NAME_ALREADY_USED, "Todo name '" + name + "' already in use.");
        }

        this.todos.remove(foundById.get());
        this.todos.add(todo);
    }

    @Override
    public void delete(UUID id) throws Exception {
        Optional<Todo> todo = this.findById(id);
        if (todo.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + id + "' not found.");
        }

        this.todos.remove(todo.get());
    }
}
