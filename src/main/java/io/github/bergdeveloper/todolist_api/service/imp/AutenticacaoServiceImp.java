package io.github.bergdeveloper.todolist_api.service.imp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.bergdeveloper.todolist_api.dto.AuthDTO;
import io.github.bergdeveloper.todolist_api.entity.Usuario;
import io.github.bergdeveloper.todolist_api.repository.UsuarioRepository;
import io.github.bergdeveloper.todolist_api.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class AutenticacaoServiceImp implements AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return usuarioRepository.findByCpf(cpf);
    }

    @Override
    public String obter_token(AuthDTO authDTO) {
        Usuario usuario = usuarioRepository.findByCpf(authDTO.cpf());
        return gerar_token_Jwt(usuario);
    }

    public String gerar_token_Jwt(Usuario usuario){
        try{
            //modificar senha do algoritmo
            Algorithm algorithm = Algorithm.HMAC256("senha_algoritmo_encriptacao");

            return JWT.create()
                    .withIssuer("To-Do-List")
                    .withSubject(usuario.getCpf())
                    .withExpiresAt(gerar_data_expiracao())
                    .sign(algorithm);
        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao tentar gerar o token. " + exception.getMessage());
        }
    }

    private Instant gerar_data_expiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validar_token_jwt(String token){
        try{
            //modificar senha do algoritmo
            Algorithm algorithm = Algorithm.HMAC256("senha_algoritmo_encriptacao");

            return JWT.require(algorithm)
                    .withIssuer("To-Do-List")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception){
            throw new RuntimeException("Token inválido. " + exception.getMessage());
        }
    }
}