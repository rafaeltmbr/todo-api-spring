package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.Optional;


public record CreateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(Params params) throws Exception {
        var findByNameParams = new TodoRepository.FindByNameParams(params.userId, params.name);
        Optional<Todo> found = this.todoRepository.findByName(findByNameParams);
        if (found.isPresent()) {
            throw new TodoException(TodoException.Type.TODO_NAME_ALREADY_USED, "Todo name '" + params.name + "' already in use.");
        }

        var createParams = new TodoRepository.CreateParams(params.userId, params.name);
        return this.todoRepository.create(createParams);
    }

    public record Params(Id userId, TodoName name) {
    }
}
