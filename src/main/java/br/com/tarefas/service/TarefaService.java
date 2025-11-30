package br.com.tarefas.service;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.exception.TarefaNotFound;
import br.com.tarefas.repository.TarefaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final ModelMapper modelMapper;

    public TarefaService(TarefaRepository tarefaRepository, ModelMapper modelMapper) {
        this.tarefaRepository = tarefaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TarefaDTO adicionarTarefa(TarefaDTO tarefa) {
        Tarefa tarefaEntity = modelMapper.map(tarefa, Tarefa.class);
        return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
    }

    public TarefaDTO recuperarTarefaId(Long id) {
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        TarefaDTO tarefaDTO = modelMapper.map(tarefaOp.orElseThrow(
                        () -> new TarefaNotFound("Tarefa com ID: " + id + " não encontrado")),
                TarefaDTO.class);
        return tarefaDTO;
    }

    public List<TarefaDTO> recuperarTarefa() {

        return modelMapper.map( tarefaRepository.findAll(), new TypeToken<TarefaDTO>() {}.getType());
    }

    public TarefaDTO atualizarTarefa(Long id, TarefaDTO tarefa) {
        Tarefa tarefaEntity = modelMapper.map(tarefa, Tarefa.class);
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        if (tarefaOp.isPresent()) {
            tarefaEntity.setId(id);
            return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
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
