package com.br.marcos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks = new ArrayList<Task>();

}
