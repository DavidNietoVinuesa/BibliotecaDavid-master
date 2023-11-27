package io.bootify.biblioteca_david.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PrestamoDTO {

    private Long id;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    @Size(max = 255)
    private String fechaDevolucion;

    @NotNull
    private EstadoPrestamo estado;

    private Long libro;

    private Long lector;

}
