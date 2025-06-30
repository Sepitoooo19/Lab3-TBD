package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.CoverageAreaDocument;
import bdavanzadas.lab1.documentRepositories.CoverageAreaDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con áreas de cobertura.
 * Proporciona métodos para buscar, crear, actualizar y eliminar
 * áreas de cobertura del sistema.
 */
@Service
public class CoverageAreaDocumentService {

    private final CoverageAreaDocumentRepository coverageAreaRepository;

    /**
     * Constructor que inyecta el repositorio de áreas de cobertura.
     * @param coverageAreaRepository Repositorio de áreas de cobertura
     */
    @Autowired
    public CoverageAreaDocumentService(CoverageAreaDocumentRepository coverageAreaRepository) {
        this.coverageAreaRepository = coverageAreaRepository;
    }

    /**
     * Obtiene todas las áreas de cobertura registradas.
     * @return Lista de todas las áreas de cobertura
     *         Retorna lista vacía si no hay áreas registradas
     */
    public List<CoverageAreaDocument> getAll() {
        return coverageAreaRepository.findAll();
    }

    /**
     * Busca un área de cobertura por su ID de documento.
     * @param id ID del documento del área de cobertura
     * @return Optional que contiene el área de cobertura si existe
     */
    public Optional<CoverageAreaDocument> getById(String id) {
        return coverageAreaRepository.findById(id);
    }

    /**
     * Verifica si existe un área de cobertura con el ID especificado.
     * @param coverageAreaId ID numérico del área de cobertura
     * @return true si existe, false en caso contrario
     */
    public boolean existsByCoverageAreaId(Integer coverageAreaId) {
        return coverageAreaRepository.existsByCoverageAreaId(coverageAreaId);
    }

    /**
     * Guarda o actualiza un área de cobertura.
     * @param document Documento del área de cobertura a guardar
     * @return Área de cobertura guardada/actualizada
     */
    public CoverageAreaDocument save(CoverageAreaDocument document) {
        return coverageAreaRepository.save(document);
    }

    /**
     * Elimina un área de cobertura por su ID de documento.
     * @param id ID del documento del área de cobertura a eliminar
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe el área con el ID especificado
     */
    public void deleteById(String id) {
        coverageAreaRepository.deleteById(id);
    }
}