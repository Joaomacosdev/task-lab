package br.com.tarefas.handler;

import br.com.tarefas.dto.ErroResponse;
import br.com.tarefas.dto.ValidateError;
import br.com.tarefas.exception.ApiExceptionContrato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponse> handleApeException(ApiExceptionContrato contrato) {
        HttpStatus status = contrato.getHttpStatus();

        ErroResponse erro = new ErroResponse(
                contrato.getCode(),
                contrato.getMessage(),
                status.value()
        );

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationException(MethodArgumentNotValidException validException) {
        List<ValidateError> erros = validException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidateError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        ErroResponse erro = new ErroResponse(
                "FIELD_VALIDATE_ERROR",
                "Existe campos não preenchidos corretamente",
                HttpStatus.BAD_REQUEST.value(),
                erros

        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
        log.error("OCORREU UM ERRO INTERNO: " + ex.getMessage(), ex);
        log.error("OCORREU UM ERRO INTERNO CAUSA: " + ex.getCause(), ex);


        ErroResponse erro = new ErroResponse(
                "INTERNAL_SERVER_ERROR",
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}