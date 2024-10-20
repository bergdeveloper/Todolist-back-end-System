package io.github.bergdeveloper.todolist_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TarefaDTO(
        String nome,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data_de_execucao_da_tarefa,
        Integer prioridade
        ) {
}
