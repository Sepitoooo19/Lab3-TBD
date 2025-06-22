package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.CoverageAreaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverageAreaDocumentRepository extends MongoRepository<CoverageAreaDocument, String> {

    // No necesitas declarar nada si sólo usas CRUD básico.
    // MongoRepository ya incluye:
    // - findAll()
    // - findById(String id)
    // - save()
    // - deleteById()
}