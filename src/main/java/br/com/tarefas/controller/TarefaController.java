package br.com.tarefas.controller;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.repository.TarefaRepository;
import br.com.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> adicionarTarefa(@Valid @RequestBody TarefaDTO tarefa) {
         TarefaDTO tarefaDTO = tarefaService.adicionarTarefa(tarefa);
        return ResponseEntity.ok().body(tarefaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> recuperarTarefa(@PathVariable Long id) {
        return ResponseEntity.ok().body(tarefaService.recuperarTarefaId(id));

    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> recuperarTarefas() {
        return ResponseEntity.ok().body(tarefaService.recuperarTarefa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TarefaDTO tarefa) {
        tarefaService.atualizarTarefa(id, tarefa);
        return ResponseEntity.ok().body(tarefa);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       tarefaService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
