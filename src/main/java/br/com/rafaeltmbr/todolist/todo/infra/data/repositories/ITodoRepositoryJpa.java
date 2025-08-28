package br.com.rafaeltmbr.todolist.todo.infra.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITodoRepositoryJpa extends JpaRepository<TodoEntityJpa, UUID> {
    Optional<TodoEntityJpa> findByUserIdAndName(UUID userId, String name);

    List<TodoEntityJpa> findAllByUserId(UUID userId);

    Optional<TodoEntityJpa> findByUserIdAndId(UUID userId, UUID todoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM todo WHERE todo.user_id = ?1 AND todo.id = ?2;", nativeQuery = true)
    void deleteByUserIdAndId(UUID userId, UUID todoId);
}
