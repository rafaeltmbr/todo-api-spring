package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.Optional;

public record DeleteTodoUseCase(TodoRepository todoRepository) {

    public void execute(Id id) throws Exception {
        Optional<Todo> found = this.todoRepository.findById(id);
        if (found.isEmpty()) {
            throw new TodoException(TodoException.Type.TODO_NOT_FOUND, "Todo with id '" + id + "' not found.");
        }

        this.todoRepository.delete(id);
    }
}
