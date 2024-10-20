package io.github.bergdeveloper.todolist_api.repository;

import io.github.bergdeveloper.todolist_api.entity.Usuario;
import io.github.bergdeveloper.todolist_api.service.UsuarioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByCpf(String cpf);
}