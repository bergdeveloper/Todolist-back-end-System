package io.github.bergdeveloper.todolist_api.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao_tarefa")
    private LocalDateTime data_criacao_tarefa;

    @Column(name = "data_de_execucao_da_tarefa")
    private LocalDateTime data_de_execucao_da_tarefa;

    @Column(name = "realizada")
    private boolean realizada;

    @Column(name = "prioridade")
    private Integer prioridade;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
