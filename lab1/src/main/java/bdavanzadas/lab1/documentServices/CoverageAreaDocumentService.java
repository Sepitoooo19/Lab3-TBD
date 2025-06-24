package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.CoverageAreaDocument;
import bdavanzadas.lab1.documentRepositories.CoverageAreaDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoverageAreaDocumentService {

    private final CoverageAreaDocumentRepository coverageAreaRepository;

    @Autowired
    public CoverageAreaDocumentService(CoverageAreaDocumentRepository coverageAreaRepository) {
        this.coverageAreaRepository = coverageAreaRepository;
    }

    public List<CoverageAreaDocument> getAll() {
        return coverageAreaRepository.findAll();
    }

    public Optional<CoverageAreaDocument> getById(String id) {
        return coverageAreaRepository.findById(id);
    }

    public boolean existsByCoverageAreaId(Integer coverageAreaId) {
        return coverageAreaRepository.existsByCoverageAreaId(coverageAreaId);
    }

    public CoverageAreaDocument save(CoverageAreaDocument document) {
        return coverageAreaRepository.save(document);
    }

    public void deleteById(String id) {
        coverageAreaRepository.deleteById(id);
    }
}
