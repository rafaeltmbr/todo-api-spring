package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.Optional;

public record DeleteTodoUseCase(TodoRepository todoRepository) {

    public void execute(Params params) throws Exception {
        var findByIdParams = new TodoRepository.FindByIdParams(params.userId, params.todoId);
        Optional<Todo> found = this.todoRepository.findById(findByIdParams);
        if (found.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + params.todoId + "' not found.");
        }

        var deleteParams = new TodoRepository.DeleteParams(params.userId, params.todoId);
        this.todoRepository.delete(deleteParams);
    }

    public record Params(
            Id userId,
            Id todoId
    ) {
    }
}
