package io.github.bergdeveloper.todolist_api.config;

import io.github.bergdeveloper.todolist_api.entity.Usuario;
import io.github.bergdeveloper.todolist_api.repository.UsuarioRepository;
import io.github.bergdeveloper.todolist_api.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = extrair_token_header(request);

        if(token != null){
            String cpf = autenticacaoService.validar_token_jwt(token);
            Usuario usuario = usuarioRepository.findByCpf(cpf);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }else{
            System.out.println("Usuário não existe no banco de dados.");
        }

        filterChain.doFilter(request, response);
    }

    public String extrair_token_header(HttpServletRequest request){
        var auth_header = request.getHeader("Authorization");

        if(auth_header == null){
            return null;
        }

        if(!auth_header.split(" ")[0].equals("Bearer")){
            return null;
        }

        return auth_header.split(" ")[1];
    }
}
