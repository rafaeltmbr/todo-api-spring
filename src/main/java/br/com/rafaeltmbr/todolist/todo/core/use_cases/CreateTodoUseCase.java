package br.com.rafaeltmbr.todolist.todo.core.use_cases;

import br.com.rafaeltmbr.todolist.todo.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.todo.core.dtos.CreateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.NameAlreadyUsedException;

import java.util.Optional;


public record CreateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(CreateTodoDto dto) throws Exception {
        var name = new TodoName(dto.name());

        Optional<Todo> found = this.todoRepository.findByName(name);
        if (found.isPresent()) {
            throw new NameAlreadyUsedException();
        }

        return this.todoRepository.create(name);
    }
}
