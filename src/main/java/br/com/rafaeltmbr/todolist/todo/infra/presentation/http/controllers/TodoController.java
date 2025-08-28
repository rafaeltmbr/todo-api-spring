package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.CreateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.DeleteTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.UpdateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.ITodoRepositoryJpa;
import br.com.rafaeltmbr.todolist.todo.infra.di.TodoContainer;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.infra.data.repositories.IUserRepositoryJpa;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private TodoContainer todoContainer;

    @Autowired
    private ITodoRepositoryJpa todoRepositoryJpaInterface;

    @Autowired
    private IUserRepositoryJpa userRepositoryJpaInterface;

    @PostConstruct
    public void init() throws Exception {
        todoContainer = TodoContainer.getInstance(todoRepositoryJpaInterface);
    }

    @GetMapping("")
    public List<TodoResponseBody> list(
            HttpServletRequest request,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var user = (User) request.getAttribute("user");
        List<Todo> todos = todoContainer.useCases.listTodos().execute(user.getId());
        return todos.stream().map(TodoResponseBody::new).toList();
    }

    @PostMapping("")
    public TodoResponseBody create(
            HttpServletRequest request,
            @RequestBody CreateTodoRequestBody body,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var user = (User) request.getAttribute("user");
        var params = new CreateTodoUseCase.Params(user.getId(), new TodoName(body.name()));
        Todo todo = todoContainer.useCases.createTodo().execute(params);
        return new TodoResponseBody(todo);
    }

    @PutMapping("/{id}")
    public TodoResponseBody update(
            HttpServletRequest request,
            @PathVariable("id") String id, @RequestBody UpdateTodoRequestBody body,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var user = (User) request.getAttribute("user");
        var params = new UpdateTodoUseCase.Params(user.getId(), new Id(id), new TodoName(body.name()), body.done());
        Todo todo = todoContainer.useCases.updateTodo().execute(params);
        return new TodoResponseBody(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(
            HttpServletRequest request,
            @PathVariable("id") String todoId,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) throws Exception {
        var user = (User) request.getAttribute("user");
        var params = new DeleteTodoUseCase.Params(user.getId(), new Id(todoId));
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
