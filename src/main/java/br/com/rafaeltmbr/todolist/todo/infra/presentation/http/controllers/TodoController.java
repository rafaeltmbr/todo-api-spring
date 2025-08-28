package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.CreateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.DeleteTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.UpdateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.ITodoRepositoryJpa;
import br.com.rafaeltmbr.todolist.todo.infra.di.TodoContainer;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;
import br.com.rafaeltmbr.todolist.user.infra.data.repositories.IUserRepositoryJpa;
import br.com.rafaeltmbr.todolist.user.infra.di.UserContainer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private TodoContainer todoContainer;
    private UserContainer userContainer;

    @Autowired
    private ITodoRepositoryJpa todoRepositoryJpaInterface;

    @Autowired
    private IUserRepositoryJpa userRepositoryJpaInterface;

    @PostConstruct
    public void init() throws Exception {
        todoContainer = TodoContainer.getInstance(todoRepositoryJpaInterface);
        userContainer = UserContainer.getInstance(userRepositoryJpaInterface);
    }

    @GetMapping("")
    public List<TodoResponseBody> list(
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var token = new UserAuthenticationToken(authorizationHeader.split(" ")[1]);
        Id userId = userContainer.useCases.authenticateUser().execute(token);

        List<Todo> todos = todoContainer.useCases.listTodos().execute(userId);
        return todos.stream().map(TodoResponseBody::new).toList();
    }

    @PostMapping("")
    public TodoResponseBody create(
            @RequestBody CreateTodoRequestBody body,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var token = new UserAuthenticationToken(authorizationHeader.split(" ")[1]);
        Id userId = userContainer.useCases.authenticateUser().execute(token);

        var params = new CreateTodoUseCase.Params(userId, new TodoName(body.name()));
        Todo todo = todoContainer.useCases.createTodo().execute(params);
        return new TodoResponseBody(todo);
    }

    @PutMapping("/{id}")
    public TodoResponseBody update(
            @PathVariable("id") String id, @RequestBody UpdateTodoRequestBody body,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var token = new UserAuthenticationToken(authorizationHeader.split(" ")[1]);
        Id userId = userContainer.useCases.authenticateUser().execute(token);

        var params = new UpdateTodoUseCase.Params(userId, new Id(id), new TodoName(body.name()), body.done());
        Todo todo = todoContainer.useCases.updateTodo().execute(params);
        return new TodoResponseBody(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") String todoId,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var token = new UserAuthenticationToken(authorizationHeader.split(" ")[1]);
        Id userId = userContainer.useCases.authenticateUser().execute(token);
        var params = new DeleteTodoUseCase.Params(userId, new Id(todoId));
        todoContainer.useCases.deleteTodo().execute(params);
    }

    public record CreateTodoRequestBody(String name) {
    }

    public record TodoResponseBody(
            String id,
            String name,
            boolean done,
            String user_id,
            String created_at
    ) {
        public TodoResponseBody(Todo todo) {
            this(
                    todo.getId().toString(),
                    todo.getName().getValue(),
                    todo.getDone(),
                    todo.getUserId().toString(),
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
