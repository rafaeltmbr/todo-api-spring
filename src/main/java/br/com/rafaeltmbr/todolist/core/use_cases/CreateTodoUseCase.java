package br.com.rafaeltmbr.todolist.core.use_cases;

import br.com.rafaeltmbr.todolist.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.core.dtos.CreateTodoDto;
import br.com.rafaeltmbr.todolist.core.entities.Name;
import br.com.rafaeltmbr.todolist.core.entities.Todo;
import br.com.rafaeltmbr.todolist.core.exceptions.NameAlreadyUsedException;

import java.util.Optional;


public record CreateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(CreateTodoDto dto) throws Exception {
        var name = new Name(dto.name());

        Optional<Todo> found = this.todoRepository.findByName(name);
        if (found.isPresent()) {
            throw new NameAlreadyUsedException();
        }

        return this.todoRepository.create(name);
    }
}
