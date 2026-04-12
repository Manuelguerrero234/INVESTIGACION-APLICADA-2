package com.universidad.grupo05.exception;

import com.universidad.grupo05.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. ATRAHA CAMPOS VACÍOS O Mal seteados
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(Exception ex) {
        List<String> details;

        if (ex instanceof MethodArgumentNotValidException) {
            details = ((MethodArgumentNotValidException) ex).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' es obligatorio: " + err.getDefaultMessage())
                    .collect(Collectors.toList());
        } else {
            details = Collections.singletonList("El cuerpo de la petición no puede estar vacío o tiene un formato inválido.");
        }

        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación: existen campos obligatorios vacíos",
                details
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 2. Maneja el 404 (ID no encontrado)
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(RecursoNoEncontradoException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Collections.singletonList("El ID proporcionado no existe en los registros.")
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 3. Maneja códigos duplicados (Error 409)
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDatabaseError(org.springframework.dao.DataIntegrityViolationException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                "Error de integridad",
                Collections.singletonList("El código del producto ya existe.")
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // 4. El "seguro" final (Si esto sale, es algo muy grave del servidor)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                Collections.singletonList("Error inesperado: " + ex.getLocalizedMessage())
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}