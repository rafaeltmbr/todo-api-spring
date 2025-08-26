package br.com.rafaeltmbr.todolist.user.infra.services;

import br.com.rafaeltmbr.todolist.user.core.entities.UserPassword;
import br.com.rafaeltmbr.todolist.user.core.entities.UserPasswordHash;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import br.com.rafaeltmbr.todolist.user.core.services.UserPasswordHashService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserPasswordHashServiceBcrypt implements UserPasswordHashService {
    @Override
    public UserPasswordHash generateHash(UserPassword password) throws UserException {
        var encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(password.getValue());
        return new UserPasswordHash(encoded);
    }

    @Override
    public boolean checkMatch(UserPassword password, UserPasswordHash hash) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.matches(password.getValue(), hash.getValue());
    }
}
