package io.github.bergdeveloper.todolist_api.service.imp;


import io.github.bergdeveloper.todolist_api.dto.TarefaDTO;
import io.github.bergdeveloper.todolist_api.entity.Tarefa;
import io.github.bergdeveloper.todolist_api.repository.TarefaRepository;
import io.github.bergdeveloper.todolist_api.repository.UsuarioRepository;
import io.github.bergdeveloper.todolist_api.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TarefaServiceImp implements TarefaService {

    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;


    @Transactional
    public TarefaDTO salvar(TarefaDTO tarefaDTO, Authentication authentication){

        var usuario = usuarioRepository.findByCpf(authentication.getName());

        if(usuario == null){
            System.out.println("Usuário não localizado.");
            return null;
        }


        if(tarefaDTO.prioridade() < 1 || tarefaDTO.prioridade() > 3){
            System.out.println("Escolha uma prioridade entre 1 e 3, sendo 1 a mais importante e 3 a menos importante.");
            return null;
        }


        if (LocalDateTime.now().isAfter(tarefaDTO.data_de_execucao_da_tarefa())) {
            System.out.println("O agendamento da tarefa deve ser feito com data e hora posteriores às atuais.");
            return null;
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setNome(tarefaDTO.nome());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setPrioridade(tarefaDTO.prioridade());
        tarefa.setData_criacao_tarefa(LocalDateTime.now());

        tarefa.setData_de_execucao_da_tarefa(tarefaDTO.data_de_execucao_da_tarefa());
        tarefa.setUsuario(usuario);

        tarefaRepository.save(tarefa);
        return tarefaDTO;
    }

    @Override
    public List<Tarefa> listas_tarefas(Authentication authentication) {
        var usuario = usuarioRepository.findByCpf(authentication.getName());

        if(usuario == null){
            System.out.println("Usuário não localizado.");
            return null;
        }

        return usuario.getTarefas();

    }

    @Override
    public Tarefa deletar_tarefa(Long id, Authentication authentication) {

        var tarefa = tarefaRepository.findById(id.intValue()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (tarefa.getUsuario().getCpf().equals(authentication.getName())){
            tarefaRepository.deleteById(id.intValue());
        }else{
            System.out.println("Você não tem permissão para deletar a tarefa.");
            return null;
        }

        return tarefa;
    }
}