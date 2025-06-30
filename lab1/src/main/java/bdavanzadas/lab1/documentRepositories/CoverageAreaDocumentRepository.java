package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.CoverageAreaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio para manejar documentos de áreas de cobertura en MongoDB.
 * Proporciona métodos para verificar la existencia de áreas de cobertura por ID.
 */
@Repository
public interface CoverageAreaDocumentRepository extends MongoRepository<CoverageAreaDocument, String> {


    /**
     * Verifica si existe un área de cobertura por su ID.
     * @param coverageAreaId ID del área de cobertura.
     * @return true si el área de cobertura existe, false en caso contrario.
     */
    boolean existsByCoverageAreaId(Integer coverageAreaId);
}