package com.universidad.grupo05.dto;

import com.universidad.grupo05.validation.CodigoProductoValido;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoRequestDTO {

    @NotNull(message = "El código del producto es obligatorio")
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres")
    @CodigoProductoValido
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser 0 o mayor")
    private Double precio;
}