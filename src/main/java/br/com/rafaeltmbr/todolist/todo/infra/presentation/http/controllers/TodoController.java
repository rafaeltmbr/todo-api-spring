package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.dtos.CreateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.dtos.UpdateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.jpa.ITodoRepositoryJpa;
import br.com.rafaeltmbr.todolist.todo.infra.di.AppContainer;
import br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities.CreateTodoRequestBody;
import br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities.TodoResponseBody;
import br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities.UpdateTodoRequestBody;
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
        var appContainer = AppContainer.getInstance(todoRepositoryJpaInterface);
        List<Todo> todos = appContainer.useCases.listTodos().execute();
        return todos.stream().map(TodoResponseBody::new).toList();
    }

    @PostMapping("")
    public TodoResponseBody create(@RequestBody CreateTodoRequestBody body) throws Exception {
        var appContainer = AppContainer.getInstance(todoRepositoryJpaInterface);
        var dto = new CreateTodoDto(new TodoName(body.name()));
        Todo todo = appContainer.useCases.createTodo().execute(dto);
        return new TodoResponseBody(todo);
    }

    @PutMapping("/{id}")
    public TodoResponseBody update(@PathVariable("id") String id, @RequestBody UpdateTodoRequestBody params) throws Exception {
        var appContainer = AppContainer.getInstance(todoRepositoryJpaInterface);
        var dto = new UpdateTodoDto(new Id(id), new TodoName(params.name()), params.done());
        Todo todo = appContainer.useCases.updateTodo().execute(dto);
        return new TodoResponseBody(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) throws Exception {
        var appContainer = AppContainer.getInstance(todoRepositoryJpaInterface);
        appContainer.useCases.deleteTodo().execute(new Id(id));
    }
}
