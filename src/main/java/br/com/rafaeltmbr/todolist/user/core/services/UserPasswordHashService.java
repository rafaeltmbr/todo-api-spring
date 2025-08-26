package br.com.rafaeltmbr.todolist.user.core.services;

import br.com.rafaeltmbr.todolist.user.core.entities.UserPassword;
import br.com.rafaeltmbr.todolist.user.core.entities.UserPasswordHash;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

public interface UserPasswordHashService {
    UserPasswordHash generateHash(UserPassword password) throws UserException;

    boolean checkMatch(UserPassword password, UserPasswordHash hash);
}
