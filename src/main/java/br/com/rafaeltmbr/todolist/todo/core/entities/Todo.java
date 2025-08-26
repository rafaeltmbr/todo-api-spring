package br.com.rafaeltmbr.todolist.todo.core.entities;

import br.com.rafaeltmbr.todolist.todo.core.exceptions.TodoException;

import java.util.UUID;

public class Todo {
    private UUID id;
    private TodoName name;
    private boolean done;

    public Todo(UUID id, TodoName name, boolean done) throws Exception {
        setId(id);
        setName(name);
        setDone(done);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) throws Exception {
        this.id = id;
        if (this.id == null) {
            throw new TodoException(TodoException.Type.TODO_INVALID_STATE, "Todo id must not be null.");
        }
    }

    public TodoName getName() {
        return name;
    }

    public void setName(TodoName name) throws Exception {
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

    @Override
    public String toString() {
        return "Todo(id=" + id.toString() + ", name='" + name.getValue() + "', done=" + done + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() != getClass()) return false;

        var other = (Todo) obj;

        return id.equals(other.id) && name.equals(other.name) && done == other.done;
    }
}
