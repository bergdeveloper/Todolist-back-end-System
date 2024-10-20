package io.github.bergdeveloper.todolist_api.controller;

import io.github.bergdeveloper.todolist_api.dto.AuthDTO;
import io.github.bergdeveloper.todolist_api.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/logar")
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDTO authDTO){
        var usuario_autenticado_token = new UsernamePasswordAuthenticationToken(authDTO.cpf(), authDTO.senha());
        authenticationManager.authenticate(usuario_autenticado_token);
        return autenticacaoService.obter_token(authDTO);
    }
}
