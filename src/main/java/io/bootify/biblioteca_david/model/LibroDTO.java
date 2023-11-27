package io.bootify.biblioteca_david.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LibroDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String titulo;

    @NotNull
    @Size(max = 255)
    private String autor;

    @NotNull
    @Size(max = 255)
    private String genero;

    @NotNull
    private EstadoLibro estado;

}
