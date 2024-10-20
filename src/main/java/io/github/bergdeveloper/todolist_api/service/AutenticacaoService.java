package io.github.bergdeveloper.todolist_api.service;

import io.github.bergdeveloper.todolist_api.dto.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {
    public String obter_token(AuthDTO authDTO);
    public String validar_token_jwt(String token);
}