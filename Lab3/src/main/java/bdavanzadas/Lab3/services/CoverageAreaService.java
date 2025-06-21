package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.CoverageAreaEntity;
import bdavanzadas.Lab3.repositories.CoverageAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoverageAreaService {

    private final CoverageAreaRepository coverageAreaRepository;

    @Autowired
    public CoverageAreaService(CoverageAreaRepository coverageAreaRepository) {
        this.coverageAreaRepository = coverageAreaRepository;
    }

    /**
     * Obtener todas las áreas de cobertura
     */
    public List<CoverageAreaEntity> findAll() {
        return coverageAreaRepository.findAll();
    }

    /**
     * Buscar área de cobertura por ID
     */
    public Optional<CoverageAreaEntity> findById(String id) {
        return coverageAreaRepository.findById(id);
    }

    /**
     * Guardar nueva área de cobertura
     */
    public CoverageAreaEntity save(CoverageAreaEntity area) {
        return coverageAreaRepository.save(area);
    }

    /**
     * Actualizar un área de cobertura
     */
    public CoverageAreaEntity update(String id, CoverageAreaEntity area) {
        area.setId(id);
        return coverageAreaRepository.save(area);
    }

    /**
     * Eliminar un área de cobertura
     */
    public void delete(String id) {
        coverageAreaRepository.deleteById(id);
    }
}
