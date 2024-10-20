package io.github.bergdeveloper.todolist_api.repository;

import io.github.bergdeveloper.todolist_api.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}