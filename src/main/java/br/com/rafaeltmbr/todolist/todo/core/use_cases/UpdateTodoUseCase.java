package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.Optional;


public record UpdateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(Params params) throws Exception {
        Id id = params.id();
        TodoName name = params.name();
        boolean done = params.done();

        Optional<Todo> foundById = this.todoRepository.findById(id);
        if (foundById.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + id + "' not found.");
        }

        Optional<Todo> foundByName = this.todoRepository.findByName(name);
        if (foundByName.isPresent() && !foundByName.get().getId().equals(id)) {
            throw new TodoException(TodoException.Type.TODO_NAME_ALREADY_USED, "Todo name '" + name + "' already in use.");
        }

        var updateParams = new TodoRepository.UpdateParams(id, name, done);
        return this.todoRepository.update(updateParams);
    }

    public record Params(
            Id id,
            TodoName name,
            boolean done
    ) {
    }
}