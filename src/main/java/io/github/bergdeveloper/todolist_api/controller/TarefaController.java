package io.github.bergdeveloper.todolist_api.controller;

import io.github.bergdeveloper.todolist_api.dto.TarefaDTO;
import io.github.bergdeveloper.todolist_api.dto.UsuarioDTO;
import io.github.bergdeveloper.todolist_api.entity.Tarefa;
import io.github.bergdeveloper.todolist_api.service.TarefaService;
import io.github.bergdeveloper.todolist_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping("/tarefas")
    private TarefaDTO salvar(@RequestBody TarefaDTO tarefaDTO, Authentication authentication){
        return tarefaService.salvar(tarefaDTO, authentication);
    }

    @GetMapping("/tarefas/lista")
    private List<Tarefa> listas_tarefas(Authentication authentication){
        return tarefaService.listas_tarefas(authentication);
    }

    @DeleteMapping("/tarefas/deletar/{id}")
    private Tarefa deletar_tarefa(@PathVariable("id") Long id, Authentication authentication){
        return tarefaService.deletar_tarefa(id, authentication);
    }
}