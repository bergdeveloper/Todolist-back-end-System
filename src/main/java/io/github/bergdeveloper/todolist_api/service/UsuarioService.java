package io.github.bergdeveloper.todolist_api.service;

import io.github.bergdeveloper.todolist_api.dto.UsuarioDTO;
import io.github.bergdeveloper.todolist_api.entity.Tarefa;
import io.github.bergdeveloper.todolist_api.entity.Usuario;
import org.springframework.security.core.Authentication;

public interface UsuarioService {
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO);
    public String deletar_usuario(Long id, Authentication authentication);
}