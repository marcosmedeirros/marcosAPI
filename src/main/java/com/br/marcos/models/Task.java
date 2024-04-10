package com.br.marcos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "task")
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne //chave estrangeira, onde ele pode ter varias tarefas
    @JoinColumn(name = "usuario_id", nullable = false, unique = false)
    private Usuario usuario;

    @Column(name = "descricao", nullable = false, length = 200)
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 200)
    private String descricao;


}
