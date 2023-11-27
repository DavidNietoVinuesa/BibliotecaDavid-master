package io.bootify.biblioteca_david.repos;

import io.bootify.biblioteca_david.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibroRepository extends JpaRepository<Libro, Long> {
}
