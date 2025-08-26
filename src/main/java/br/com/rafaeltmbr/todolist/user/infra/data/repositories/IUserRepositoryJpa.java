package br.com.rafaeltmbr.todolist.user.infra.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepositoryJpa extends JpaRepository<UserEntityJpa, UUID> {
    Optional<UserEntityJpa> findByEmail(String email);
}
