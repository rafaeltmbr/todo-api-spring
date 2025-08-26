package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

import java.util.UUID;

public class User {
    private UUID id;
    private UserName name;
    private Email email;
    private UserPasswordHash passwordHash;

    public User(UUID id, UserName name, Email email, UserPasswordHash passwordHash) throws Exception {
        setId(id);
        setName(name);
        setEmail(email);
        setPasswordHash(passwordHash);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) throws Exception {
        this.id = id;
        if (this.id == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User id must not be null.");
        }
    }

    public UserName getName() {
        return name;
    }

    public void setName(UserName name) throws Exception {
        this.name = name;
        if (this.name == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User name must not be null.");
        }
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) throws Exception {
        this.email = email;
        if (this.email == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User email must not be null.");
        }
    }

    public UserPasswordHash getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(UserPasswordHash passwordHash) throws Exception {
        this.passwordHash = passwordHash;
        if (this.passwordHash == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User password hash must not be null.");
        }
    }

    @Override
    public String toString() {
        return "User(id=" + id.toString() + ", name='" + name.toString() + "', email=" + email.toString() + ", passwordHash=" + passwordHash.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() != getClass()) return false;

        var other = (User) obj;

        return id.equals(other.id) && name.equals(other.name) && email.equals(other.email) && passwordHash.equals(other.passwordHash);
    }
}
