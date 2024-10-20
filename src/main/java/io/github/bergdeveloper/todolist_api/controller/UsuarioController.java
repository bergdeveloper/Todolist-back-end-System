package io.github.bergdeveloper.todolist_api.controller;

import io.github.bergdeveloper.todolist_api.dto.UsuarioDTO;
import io.github.bergdeveloper.todolist_api.entity.Tarefa;
import io.github.bergdeveloper.todolist_api.entity.Usuario;
import io.github.bergdeveloper.todolist_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    private UsuarioDTO salvar(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.salvar(usuarioDTO);
    }

    @GetMapping("/listar-usuarios")
    private String listar_usuarios(){
        return "Permissão de administrador.";
    }

    @DeleteMapping("/deletar/{id}")
    private String deletar_tarefa(@PathVariable Long id, Authentication authentication){
        return usuarioService.deletar_usuario(id, authentication);
    }
}