package br.com.rafaeltmbr.todolist.user.core.services;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;

public interface UserAuthenticationTokenService {
    UserAuthenticationToken generateToken(Id id) throws Exception;

    Id validate(UserAuthenticationToken token) throws Exception;
}
