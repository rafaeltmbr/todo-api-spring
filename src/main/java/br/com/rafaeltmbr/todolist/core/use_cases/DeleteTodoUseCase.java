package br.com.rafaeltmbr.todolist.core.use_cases;

import br.com.rafaeltmbr.todolist.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.core.entities.Todo;
import br.com.rafaeltmbr.todolist.core.exceptions.TodoNotFoundException;

import java.util.Optional;
import java.util.UUID;

public record DeleteTodoUseCase(TodoRepository todoRepository) {

    public void execute(UUID id) throws Exception {
        Optional<Todo> found = this.todoRepository.findById(id);
        if (found.isEmpty()) {
            throw new TodoNotFoundException();
        }

        this.todoRepository.delete(id);
    }
}
