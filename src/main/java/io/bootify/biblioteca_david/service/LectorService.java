package io.bootify.biblioteca_david.service;

import io.bootify.biblioteca_david.domain.Lector;
import io.bootify.biblioteca_david.domain.Prestamo;
import io.bootify.biblioteca_david.model.LectorDTO;
import io.bootify.biblioteca_david.repos.LectorRepository;
import io.bootify.biblioteca_david.repos.PrestamoRepository;
import io.bootify.biblioteca_david.util.NotFoundException;
import io.bootify.biblioteca_david.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LectorService {

    private final LectorRepository lectorRepository;
    private final PrestamoRepository prestamoRepository;

    public LectorService(final LectorRepository lectorRepository,
            final PrestamoRepository prestamoRepository) {
        this.lectorRepository = lectorRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<LectorDTO> findAll() {
        final List<Lector> lectors = lectorRepository.findAll(Sort.by("id"));
        return lectors.stream()
                .map(lector -> mapToDTO(lector, new LectorDTO()))
                .toList();
    }

    public LectorDTO get(final Long id) {
        return lectorRepository.findById(id)
                .map(lector -> mapToDTO(lector, new LectorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LectorDTO lectorDTO) {
        final Lector lector = new Lector();
        mapToEntity(lectorDTO, lector);
        return lectorRepository.save(lector).getId();
    }

    public void update(final Long id, final LectorDTO lectorDTO) {
        final Lector lector = lectorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(lectorDTO, lector);
        lectorRepository.save(lector);
    }

    public void delete(final Long id) {
        lectorRepository.deleteById(id);
    }

    private LectorDTO mapToDTO(final Lector lector, final LectorDTO lectorDTO) {
        lectorDTO.setId(lector.getId());
        lectorDTO.setNombre(lector.getNombre());
        lectorDTO.setApellidos(lector.getApellidos());
        lectorDTO.setDireccion(lector.getDireccion());
        return lectorDTO;
    }

    private Lector mapToEntity(final LectorDTO lectorDTO, final Lector lector) {
        lector.setNombre(lectorDTO.getNombre());
        lector.setApellidos(lectorDTO.getApellidos());
        lector.setDireccion(lectorDTO.getDireccion());
        return lector;
    }

    public String getReferencedWarning(final Long id) {
        final Lector lector = lectorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Prestamo lectorPrestamo = prestamoRepository.findFirstByLector(lector);
        if (lectorPrestamo != null) {
            return WebUtils.getMessage("lector.prestamo.lector.referenced", lectorPrestamo.getId());
        }
        return null;
    }

}
