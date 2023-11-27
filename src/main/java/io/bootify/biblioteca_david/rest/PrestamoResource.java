package io.bootify.biblioteca_david.rest;

import io.bootify.biblioteca_david.model.PrestamoDTO;
import io.bootify.biblioteca_david.service.PrestamoService;
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
@RequestMapping(value = "/api/prestamos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoResource {

    private final PrestamoService prestamoService;

    public PrestamoResource(final PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> getAllPrestamos() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> getPrestamo(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(prestamoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPrestamo(@RequestBody @Valid final PrestamoDTO prestamoDTO) {
        final Long createdId = prestamoService.create(prestamoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePrestamo(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final PrestamoDTO prestamoDTO) {
        prestamoService.update(id, prestamoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePrestamo(@PathVariable(name = "id") final Long id) {
        prestamoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
