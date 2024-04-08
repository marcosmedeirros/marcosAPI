package com.br.marcos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario {
    public interface CreateUsuario {}
    public interface UpdateUsuario {}
    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id, unique = true")
    private Long id;

    @Column(name = "username", nullable = false, length = 100, unique = true)
    @NotNull(groups = CreateUsuario.class)
    @NotEmpty(groups = CreateUsuario.class)
    @Size(groups = CreateUsuario.class,min = 5, max = 100)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, length = 50)
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class})
    @NotEmpty(groups = {CreateUsuario.class, UpdateUsuario.class})
    @Size(groups = {CreateUsuario.class, UpdateUsuario.class}, min = 5, max = 60)
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Task> tasks = new ArrayList<Task>();

    public Usuario() {
    }

    public Usuario(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
