package br.com.rafaeltmbr.todolist.todo.infra.presentation.http.controllers;

import br.com.rafaeltmbr.todolist.todo.core.dtos.CreateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.dtos.UpdateTodoDto;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.jpa.ITodoRepositoryJpa;
import br.com.rafaeltmbr.todolist.todo.infra.di.AppContainer;
import br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities.TodoResponseBody;
import br.com.rafaeltmbr.todolist.todo.infra.presentation.http.entities.UpdateTodoRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public TodoResponseBody create(@RequestBody CreateTodoDto dto) throws Exception {
        var appContainer = AppContainer.getInstance(todoRepositoryJpaInterface);
        Todo todo = appContainer.useCases.createTodo().execute(dto);
        return new TodoResponseBody(todo);
    }

    @PutMapping("/{id}")
    public TodoResponseBody update(@PathVariable("id") UUID id, @RequestBody UpdateTodoRequestBody params) throws Exception {
        var appContainer = AppContainer.getInstance(todoRepositoryJpaInterface);
        var dto = new UpdateTodoDto(id, params.name(), params.done());
        Todo todo = appContainer.useCases.updateTodo().execute(dto);
        return new TodoResponseBody(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) throws Exception {
        var appContainer = AppContainer.getInstance(todoRepositoryJpaInterface);
        appContainer.useCases.deleteTodo().execute(id);
    }
}
