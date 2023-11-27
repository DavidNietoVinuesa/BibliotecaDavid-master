package io.bootify.biblioteca_david.rest;

import io.bootify.biblioteca_david.model.LibroDTO;
import io.bootify.biblioteca_david.service.LibroService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/libros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibroResource {

    private final LibroService libroService;

    public LibroResource(final LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroDTO>> getAllLibros() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> getLibro(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(libroService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLibro(@RequestBody @Valid final LibroDTO libroDTO) {
        final Long createdId = libroService.create(libroDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateLibro(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final LibroDTO libroDTO) {
        libroService.update(id, libroDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLibro(@PathVariable(name = "id") final Long id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
