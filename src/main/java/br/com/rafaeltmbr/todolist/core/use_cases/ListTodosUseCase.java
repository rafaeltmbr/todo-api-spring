package br.com.rafaeltmbr.todolist.core.use_cases;

import br.com.rafaeltmbr.todolist.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.core.entities.Todo;

import java.util.List;

public record ListTodosUseCase(TodoRepository todoRepository) {

    public List<Todo> execute() throws Exception {
        return this.todoRepository.listAll();
    }
}
