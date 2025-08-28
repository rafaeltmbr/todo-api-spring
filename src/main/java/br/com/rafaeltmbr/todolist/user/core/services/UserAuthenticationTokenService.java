package br.com.rafaeltmbr.todolist.user.core.services;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

public interface UserAuthenticationTokenService {
    UserAuthenticationToken generateToken(Id id) throws UserException;

    Id validate(UserAuthenticationToken token) throws UserException;
}
