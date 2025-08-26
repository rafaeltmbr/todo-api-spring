package br.com.rafaeltmbr.todolist.todo.core.entities;

import br.com.rafaeltmbr.todolist.common.core.entities.CreatedAt;
import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

public class Todo {
    private Id id;
    private TodoName name;
    private boolean done;
    private CreatedAt createdAt;

    public Todo(Id id, TodoName name, boolean done, CreatedAt createdAt) throws TodoException {
        setId(id);
        setName(name);
        setDone(done);
        setCreatedAt(createdAt);
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) throws TodoException {
        this.id = id;
        if (this.id == null) {
            throw new TodoException(TodoException.Type.TODO_INVALID_STATE, "Todo id must not be null.");
        }
    }

    public TodoName getName() {
        return name;
    }

    public void setName(TodoName name) throws TodoException {
        this.name = name;
        if (this.name == null) {
            throw new TodoException(TodoException.Type.TODO_INVALID_STATE, "Todo name must not be null.");
        }
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) throws TodoException {
        this.createdAt = createdAt;
        if (this.createdAt == null) {
            throw new TodoException(TodoException.Type.TODO_INVALID_STATE, "Todo createdAt must not be null.");
        }
    }

    @Override
    public String toString() {
        return "Todo(id=" + id + ", name='" + name + "', done=" + done + ", createdAt=" + createdAt + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() != getClass()) return false;

        var other = (Todo) obj;

        return id.equals(other.id) && name.equals(other.name) && done == other.done;
    }
}
