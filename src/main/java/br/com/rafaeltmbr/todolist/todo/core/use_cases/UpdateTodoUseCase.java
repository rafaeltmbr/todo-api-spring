package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.dtos.UpdateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.Optional;


public record UpdateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(UpdateTodoDto dto) throws Exception {
        Id id = dto.id();
        TodoName name = dto.name();
        boolean done = dto.done();

        Optional<Todo> foundById = this.todoRepository.findById(id);
        if (foundById.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + id + "' not found.");
        }

        Optional<Todo> foundByName = this.todoRepository.findByName(name);
        if (foundByName.isPresent() && !foundByName.get().getId().equals(id)) {
            throw new TodoException(TodoException.Type.TODO_NAME_ALREADY_USED, "Todo name '" + name + "' already in use.");
        }

        var todo = new Todo(id, name, done, foundById.get().getCreatedAt());

        this.todoRepository.update(todo);

        return todo;
    }
}