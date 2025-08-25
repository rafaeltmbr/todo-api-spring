package br.com.rafaeltmbr.todolist.todo.infra.data.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ITodoRepositoryJpa extends JpaRepository<TodoEntityJpa, UUID> {
    Optional<TodoEntityJpa> findByName(String name);
}
