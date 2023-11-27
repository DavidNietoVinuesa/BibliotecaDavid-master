package io.bootify.biblioteca_david.repos;

import io.bootify.biblioteca_david.domain.Lector;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LectorRepository extends JpaRepository<Lector, Long> {
}
