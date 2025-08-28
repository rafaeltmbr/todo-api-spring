package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;

import java.util.List;

public record ListTodosUseCase(TodoRepository todoRepository) {

    public List<Todo> execute(Id userId) throws Exception {
        var params = new TodoRepository.ListAllParams(userId);
        return this.todoRepository.listAll(params);
    }
}
