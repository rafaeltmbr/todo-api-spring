package br.com.rafaeltmbr.todolist.user.infra.data.repositories;

import br.com.rafaeltmbr.todolist.common.core.entities.CreatedAt;
import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.entities.Email;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.core.entities.UserName;
import br.com.rafaeltmbr.todolist.user.core.entities.UserPasswordHash;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "users")
public class UserEntityJpa {
    @jakarta.persistence.Id
    @GeneratedValue(generator = "UUID")
    public UUID id;

    @Column(nullable = false)
    public String name;

    @Column(unique = true, nullable = false)
    public String email;

    @Column(name = "password_hash", nullable = false)
    public String passwordHash;

    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    public LocalDateTime createdAt;

    public UserEntityJpa() {
        id = null;
        name = null;
        email = null;
        passwordHash = null;
        createdAt = null;
    }

    public UserEntityJpa(UUID id, String name, String email, String passwordHash, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public User toUser() throws Exception {
        return new User(
                new Id(id.toString()),
                new UserName(name),
                new Email(email),
                new UserPasswordHash(passwordHash),
                new CreatedAt(createdAt)
        );
    }
}
