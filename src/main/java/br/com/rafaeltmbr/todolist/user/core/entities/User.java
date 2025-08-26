package br.com.rafaeltmbr.todolist.user.core.entities;

import br.com.rafaeltmbr.todolist.common.core.entities.CreatedAt;
import br.com.rafaeltmbr.todolist.common.core.entities.Id;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;

public class User {
    private Id id;
    private UserName name;
    private Email email;
    private UserPasswordHash passwordHash;
    private CreatedAt createdAt;

    public User(Id id, UserName name, Email email, UserPasswordHash passwordHash, CreatedAt createdAt) throws UserException {
        setId(id);
        setName(name);
        setEmail(email);
        setPasswordHash(passwordHash);
        setCreatedAt(createdAt);
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) throws UserException {
        this.id = id;
        if (this.id == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User id must not be null.");
        }
    }

    public UserName getName() {
        return name;
    }

    public void setName(UserName name) throws UserException {
        this.name = name;
        if (this.name == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User name must not be null.");
        }
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) throws UserException {
        this.email = email;
        if (this.email == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User email must not be null.");
        }
    }

    public UserPasswordHash getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(UserPasswordHash passwordHash) throws UserException {
        this.passwordHash = passwordHash;
        if (this.passwordHash == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User password hash must not be null.");
        }
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) throws UserException {
        this.createdAt = createdAt;
        if (this.createdAt == null) {
            throw new UserException(UserException.Type.USER_INVALID_STATE, "User createdAt must not be null.");
        }
    }

    @Override
    public String toString() {
        return "User(id=" + id + ", name='" + name + "', email=" + email + ", passwordHash=" + passwordHash + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() != getClass()) return false;

        var other = (User) obj;

        return id.equals(other.id) && name.equals(other.name) && email.equals(other.email) && passwordHash.equals(other.passwordHash);
    }
}
