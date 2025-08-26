package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.CreateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.UpdateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.ITodoRepositoryJpa;
import br.com.rafaeltmbr.todolist.todo.infra.di.TodoContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private ITodoRepositoryJpa todoRepositoryJpaInterface;

    @GetMapping("")
    public List<TodoResponseBody> list() throws Exception {
        var appContainer = TodoContainer.getInstance(todoRepositoryJpaInterface);
        List<Todo> todos = appContainer.useCases.listTodos().execute();
        return todos.stream().map(TodoResponseBody::new).toList();
    }

    @PostMapping("")
    public TodoResponseBody create(@RequestBody CreateTodoRequestBody body) throws Exception {
        var appContainer = TodoContainer.getInstance(todoRepositoryJpaInterface);
        var params = new CreateTodoUseCase.Params(new TodoName(body.name()));
        Todo todo = appContainer.useCases.createTodo().execute(params);
        return new TodoResponseBody(todo);
    }

    @PutMapping("/{id}")
    public TodoResponseBody update(@PathVariable("id") String id, @RequestBody UpdateTodoRequestBody body) throws Exception {
        var appContainer = TodoContainer.getInstance(todoRepositoryJpaInterface);
        var params = new UpdateTodoUseCase.Params(new Id(id), new TodoName(body.name()), body.done());
        Todo todo = appContainer.useCases.updateTodo().execute(params);
        return new TodoResponseBody(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) throws Exception {
        var appContainer = TodoContainer.getInstance(todoRepositoryJpaInterface);
        appContainer.useCases.deleteTodo().execute(new Id(id));
    }

    public record CreateTodoRequestBody(String name) {
    }

    public record TodoResponseBody(
            String id,
            String name,
            boolean done,
            String created_at
    ) {
        public TodoResponseBody(Todo todo) {
            this(
                    todo.getId().toString(),
                    todo.getName().getValue(),
                    todo.getDone(),
                    todo.getCreatedAt().toString()
            );
        }
    }

    public record UpdateTodoRequestBody(
            String name,
            boolean done
    ) {
    }
}
