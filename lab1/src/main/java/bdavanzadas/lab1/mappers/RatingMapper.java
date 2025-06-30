package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.documents.RatingDocument;
import bdavanzadas.lab1.entities.RatingEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * Mapeador para convertir entidades de calificación a documentos de calificación.
 * Este mapeador transforma los datos de la entidad de calificación
 * a un documento que puede ser almacenado en MongoDB.
 */
public class RatingMapper {

    /**
     * Convierte una entidad de calificación a un documento de calificación.
     *
     * @param entity Entidad de calificación a convertir
     * @return Documento de calificación con los datos de la entidad
     */
    public static RatingDocument fromRatingEntity(RatingEntity entity) {
        RatingDocument document = new RatingDocument();

        document.setRatingId(entity.getId());
        document.setRating(entity.getRating());
        document.setDate(convertDateToLocalDate(entity.getDate()));
        document.setClientId(entity.getClientId());
        document.setDealerId(entity.getDealerId());
        document.setOrderId(entity.getOrderId());
        document.setComment(entity.getComment());

        return document;


    }

    private static java.time.LocalDate convertDateToLocalDate(java.util.Date date) {
        if (date == null) return null;
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
