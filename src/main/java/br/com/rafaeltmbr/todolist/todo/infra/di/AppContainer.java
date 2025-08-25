package br.com.rafaeltmbr.todolist.todo.infra.di;

import br.com.rafaeltmbr.todolist.todo.core.use_cases.CreateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.DeleteTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.ListTodosUseCase;
import br.com.rafaeltmbr.todolist.todo.core.use_cases.UpdateTodoUseCase;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.jpa.ITodoRepositoryJpa;
import br.com.rafaeltmbr.todolist.todo.infra.data.repositories.jpa.TodoRepositoryJpa;

public class AppContainer {
    private static AppContainer container;

    public AppUseCasesContainer useCases;
    public AppDataContainer data;

    private AppContainer() {
    }

    public static AppContainer getInstance(ITodoRepositoryJpa todoRepositoryJpaInterface) {
        if (container != null) return container;

        container = new AppContainer();

        var todoRepository = new TodoRepositoryJpa(todoRepositoryJpaInterface);

        container.data = new AppDataContainer(new AppRepositoryContainer(todoRepository));

        container.useCases = new AppUseCasesContainer(
                new ListTodosUseCase(todoRepository),
                new CreateTodoUseCase(todoRepository),
                new UpdateTodoUseCase(todoRepository),
                new DeleteTodoUseCase(todoRepository)
        );

        return container;
    }
}
