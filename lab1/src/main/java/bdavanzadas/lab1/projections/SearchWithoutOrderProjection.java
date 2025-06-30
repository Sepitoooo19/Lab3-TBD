package bdavanzadas.lab1.projections;


/**
 * Proyección para obtener el ID del cliente sin incluir información de orden.
 * Esta proyección se utiliza para consultas que requieren solo el ID del cliente
 * sin detalles adicionales sobre las órdenes.
 */
public interface SearchWithoutOrderProjection {

    /**
     * ID del cliente (viene como "clientId" desde el aggregation).
     * Este campo representa el identificador único del cliente en la base de datos.
     *
     * @return ID del cliente
     */
    Integer getClientId();
}