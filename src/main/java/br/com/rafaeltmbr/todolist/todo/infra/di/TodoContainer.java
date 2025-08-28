package br.com.rafaeltmbr.todolist.todo.infra.di;

import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.CreateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.DeleteTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.ListTodosUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.UpdateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.ITodoRepositoryJpa;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.TodoRepositoryJpa;

public class TodoContainer {
    private static TodoContainer container;

    public TodoUseCasesContainer useCases;
    public TodoDataContainer data;

    private TodoContainer() {
    }

    public static synchronized TodoContainer getInstance() throws TodoException {
        return TodoContainer.getInstance(null);
    }

    public static synchronized TodoContainer getInstance(ITodoRepositoryJpa todoRepositoryJpaInterface) throws TodoException {
        if (container != null) return container;

        if (todoRepositoryJpaInterface == null) {
            throw new TodoException(TodoException.Type.TODO_MISSING_DEPENDENCIES, "Missing todoRepositoryJpaInterface.");
        }

        container = new TodoContainer();

        var todoRepository = new TodoRepositoryJpa(todoRepositoryJpaInterface);

        container.data = new TodoDataContainer(new TodoRepositoryContainer(todoRepository));

        container.useCases = new TodoUseCasesContainer(
                new ListTodosUseCase(todoRepository),
                new CreateTodoUseCase(todoRepository),
                new UpdateTodoUseCase(todoRepository),
                new DeleteTodoUseCase(todoRepository)
        );

        return container;
    }
}
