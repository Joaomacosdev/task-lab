package br.com.tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TarefaDTO {

    private Long id;
    @NotBlank(message = "O titulo é obrigatório")
    private String titulo;
    @NotBlank(message = "A descricao é obrigatório")
    private String descricao;
    @NotBlank(message = "O local é obrigatório")
    private String local;
    @NotNull(message = "Data e Hora são obrigatórias")
    private LocalDateTime dataHora;


}
