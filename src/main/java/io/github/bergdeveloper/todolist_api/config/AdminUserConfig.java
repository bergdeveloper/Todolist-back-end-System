package io.github.bergdeveloper.todolist_api.config;

import io.github.bergdeveloper.todolist_api.entity.Usuario;
import io.github.bergdeveloper.todolist_api.enums.Role;
import io.github.bergdeveloper.todolist_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Usuario usuario = usuarioRepository.findByCpf("84795003017");
        if(usuario != null){
            System.out.println("O Usuário administrador já está cadastrado.");
        }else{
            usuario = new Usuario();
            usuario.setNome("Berg Developer");
            usuario.setCpf("84795003017");
            usuario.setSenha(passwordEncoder.encode("admin12345"));
            usuario.setRole(Role.ADMIN);
            usuarioRepository.save(usuario);
        }
    }
}
