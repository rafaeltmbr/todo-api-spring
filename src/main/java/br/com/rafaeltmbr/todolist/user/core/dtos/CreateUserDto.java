package br.com.rafaeltmbr.todolist.user.core.dtos;

import br.com.rafaeltmbr.todolist.user.core.entities.Email;
import br.com.rafaeltmbr.todolist.user.core.entities.UserName;
import br.com.rafaeltmbr.todolist.user.core.entities.UserPasswordHash;

public record CreateUserDto(
        UserName name,
        Email email,
        UserPasswordHash passwordHash
) {
}
