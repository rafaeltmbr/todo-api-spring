package br.com.rafaeltmbr.todolist.user.infra.services;

import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;
import br.com.rafaeltmbr.todolist.user.core.services.UserAuthenticationTokenService;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class UserAuthenticationTokenServiceJwt implements UserAuthenticationTokenService {
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    @Override
    public UserAuthenticationToken generateToken(Id id) throws Exception {
        String value = Jwts
                .builder()
                .subject(id.getValue())
                .signWith(key)
                .compact();

        return new UserAuthenticationToken(value);
    }

    @Override
    public Id validate(UserAuthenticationToken token) throws Exception {
        String value = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token.getValue())
                .getPayload()
                .getSubject();

        return new Id(value);
    }
}
