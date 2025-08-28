package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.Optional;


public record UpdateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(Params params) throws Exception {
        Id userId = params.userId();
        Id todoId = params.todoId();
        TodoName name = params.name();
        boolean done = params.done();

        var findByIdParams = new TodoRepository.FindByIdParams(userId, todoId);
        Optional<Todo> foundById = this.todoRepository.findById(findByIdParams);
        if (foundById.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + todoId + "' not found.");
        }

        var findByNameParams = new TodoRepository.FindByNameParams(userId, name);
        Optional<Todo> foundByName = this.todoRepository.findByName(findByNameParams);
        if (foundByName.isPresent() && !foundByName.get().getId().equals(todoId)) {
            throw new TodoException(TodoException.Type.TODO_NAME_ALREADY_USED, "Todo name '" + name + "' already in use.");
        }

        var updateParams = new TodoRepository.UpdateParams(userId, todoId, name, done);
        return this.todoRepository.update(updateParams);
    }

    public record Params(
            Id userId,
            Id todoId,
            TodoName name,
            boolean done
    ) {
    }
}