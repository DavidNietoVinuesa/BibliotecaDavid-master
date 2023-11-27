package io.bootify.biblioteca_david.repos;

import io.bootify.biblioteca_david.domain.Lector;
import io.bootify.biblioteca_david.domain.Libro;
import io.bootify.biblioteca_david.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    Prestamo findFirstByLibro(Libro libro);

    Prestamo findFirstByLector(Lector lector);

}
