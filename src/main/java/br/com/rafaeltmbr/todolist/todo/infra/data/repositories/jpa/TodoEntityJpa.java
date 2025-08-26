package br.com.rafaeltmbr.todolist.todo.infra.data.repositories.jpa;

import br.com.rafaeltmbr.todolist.common.core.entities.CreatedAt;
import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.common.core.exceptions.CommonException;
import br.com.rafaeltmbr.todolist.todo.core.entities.Todo;
import br.com.rafaeltmbr.todolist.todo.core.entities.TodoName;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "todo")
public class TodoEntityJpa {
    @jakarta.persistence.Id
    @GeneratedValue(generator = "UUID")
    public UUID id;

    @Column(unique = true)
    public String name;

    public boolean done;

    @Column(name = "created_at")
    @CreationTimestamp
    public LocalDateTime createdAt;

    public TodoEntityJpa() {
        id = null;
        name = "";
        done = false;
        createdAt = null;
    }

    public TodoEntityJpa(UUID id, String name, boolean done, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.done = done;
        this.createdAt = createdAt;
    }

    public Todo toTodo() throws TodoException, CommonException {
        return new Todo(new Id(id.toString()), new TodoName(name), done, new CreatedAt(createdAt));
    }
}
