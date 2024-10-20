package io.github.bergdeveloper.todolist_api.dto;

import io.github.bergdeveloper.todolist_api.enums.Role;

public record UsuarioDTO(
        String nome,
        String cpf,
        String senha) {
}
