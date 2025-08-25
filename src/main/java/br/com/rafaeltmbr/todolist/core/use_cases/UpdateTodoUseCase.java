package br.com.rafaeltmbr.todolist.core.use_cases;

import br.com.rafaeltmbr.todolist.core.data.repositories.TodoRepository;
import br.com.rafaeltmbr.todolist.core.dtos.UpdateTodoDto;
import br.com.rafaeltmbr.todolist.core.entities.Name;
import br.com.rafaeltmbr.todolist.core.entities.Todo;
import br.com.rafaeltmbr.todolist.core.exceptions.NameAlreadyUsedException;
import br.com.rafaeltmbr.todolist.core.exceptions.TodoNotFoundException;

import java.util.Optional;
import java.util.UUID;


public record UpdateTodoUseCase(TodoRepository todoRepository) {

    public Todo execute(UpdateTodoDto dto) throws Exception {
        UUID id = dto.id();
        var name = new Name(dto.nameValue());
        boolean done = dto.done();

        Optional<Todo> foundById = this.todoRepository.findById(id);
        if (foundById.isEmpty()) {
            throw new TodoNotFoundException();
        }

        Optional<Todo> foundByName = this.todoRepository.findByName(name);
        if (foundByName.isPresent() && !foundByName.get().id().equals(id)) {
            throw new NameAlreadyUsedException();
        }

        var todo = new Todo(id, name, done);

        this.todoRepository.update(todo);

        return todo;
    }
}