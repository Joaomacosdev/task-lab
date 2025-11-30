package br.com.tarefas.service;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.exception.TarefaNotFound;
import br.com.tarefas.mapper.TarefaMapper;
import br.com.tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaService(TarefaRepository tarefaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
    }

    @Transactional
    public TarefaDTO adicionarTarefa(TarefaDTO tarefa) {
        Tarefa tarefaEntity = tarefaMapper.toEntity(tarefa);
        return tarefaMapper.toDTO(tarefaRepository.save(tarefaEntity));
    }

    public TarefaDTO recuperarTarefaId(Long id) {
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);

        Tarefa tarefa = tarefaOp.orElseThrow(
                () -> new TarefaNotFound("Tarefa com ID: " + id + " não encontrado"));
        return tarefaMapper.toDTO(tarefa);
    }

    public List<TarefaDTO> recuperarTarefa() {

        return tarefaMapper.toDTOList(tarefaRepository.findAll());
    }

    public TarefaDTO atualizarTarefa(Long id, TarefaDTO tarefa) {
        Tarefa tarefaEntity = tarefaMapper.toEntity(tarefa);
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        if (tarefaOp.isPresent()) {
            tarefaEntity.setId(id);
            return tarefaMapper.toDTO(tarefaRepository.save(tarefaEntity));
        }
        throw new TarefaNotFound("tarefa com id: " + id + " não encontrado");
    }

    public void delete(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNotFound("Tarefa com ID: " + id + " não encontrado");
        }
        tarefaRepository.deleteById(id);

    }


}
