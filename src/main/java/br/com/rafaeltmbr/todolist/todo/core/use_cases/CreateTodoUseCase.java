package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.Optional;


public record CreateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(Params params) throws Exception {
        TodoName name = params.name();
        Optional<Todo> found = this.todoRepository.findByName(name);
        if (found.isPresent()) {
            throw new TodoException(TodoException.Type.TODO_NAME_ALREADY_USED, "Todo name '" + name + "' already in use.");
        }

        return this.todoRepository.create(new TodoRepository.CreateParams(name));
    }

    public record Params(TodoName name) {
    }
}
