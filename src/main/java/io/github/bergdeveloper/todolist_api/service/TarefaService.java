package io.github.bergdeveloper.todolist_api.service;

import io.github.bergdeveloper.todolist_api.dto.TarefaDTO;
import io.github.bergdeveloper.todolist_api.dto.UsuarioDTO;
import io.github.bergdeveloper.todolist_api.entity.Tarefa;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TarefaService {
    public TarefaDTO salvar(TarefaDTO tarefaDTO, Authentication authentication);
    public List<Tarefa> listas_tarefas(Authentication authentication);
    public Tarefa deletar_tarefa(Long id, Authentication authentication);
}