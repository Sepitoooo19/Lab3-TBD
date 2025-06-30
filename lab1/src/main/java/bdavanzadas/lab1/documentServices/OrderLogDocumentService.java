package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.OrderLogDocument;
import bdavanzadas.lab1.documentRepositories.OrderLogDocumentRepository;
import bdavanzadas.lab1.dtos.RapidChangeOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Servicio para gestionar operaciones con registros de órdenes.
 * Proporciona métodos para buscar, crear, actualizar y eliminar
 * registros de órdenes del sistema.
 */
@Service
public class OrderLogDocumentService {

    /**
     * Repositorio para acceder a los documentos de registros de órdenes.
     * Este repositorio permite realizar operaciones CRUD sobre los registros
     */
    private final OrderLogDocumentRepository repository;

    /**
     * Constructor que inyecta el repositorio de registros de órdenes.
     * @param repository Repositorio de registros de órdenes
     */
    @Autowired
    public OrderLogDocumentService(OrderLogDocumentRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todos los registros de órdenes asociados a un ID de orden específico.
     * @param orderId ID de la orden para la cual se desean obtener los registros
     * @return Lista de registros de órdenes asociados al ID de orden
     */
    public List<OrderLogDocument> getByOrderId(Integer orderId) {
        return repository.findByOrderId(orderId);
    }

    /**
     * Obtiene todos los registros de órdenes registrados en el sistema.
     * @return Lista de todos los documentos de registros de órdenes.
     *        Retorna lista vacía si no hay registros de órdenes registrados.
     */
    public List<OrderLogDocument> getAll() {
        return repository.findAll();
    }

    /**
     * Guarda un nuevo registro de orden o actualiza uno existente.
     * @param log Documento del registro de orden a guardar
     *            @return Registro de orden guardado/actualizado
     */
    public OrderLogDocument save(OrderLogDocument log) {
        return repository.save(log);
    }


    /**
     * Elimina un registro de orden por su ID de documento.
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     * Obtiene una lista de órdenes que han tenido cambios rápidos en sus registros.
     * Un cambio rápido se define como al menos 4 registros en un período de 10 minutos.
     * @return Lista de DTOs con información sobre las órdenes con cambios rápidos
     */
    public List<RapidChangeOrderDTO> getOrdersWithRapidChangesDetailed() {
        Set<Integer> uniqueOrders = repository.findAll()
                .stream()
                .map(OrderLogDocument::getOrderId)
                .collect(Collectors.toSet());

        List<RapidChangeOrderDTO> result = new ArrayList<>();

        for (Integer orderId : uniqueOrders) {
            List<OrderLogDocument> logs = repository.findAllByOrderIdOrderByTimestampAsc(orderId);
            for (int i = 0; i <= logs.size() - 4; i++) {
                LocalDateTime start = logs.get(i).getTimestamp();
                LocalDateTime end = logs.get(i + 3).getTimestamp();
                if (Duration.between(start, end).toMinutes() <= 10) {
                    result.add(new RapidChangeOrderDTO(orderId, logs.size(), start, end));
                    break;
                }
            }
        }
        return result;
    }
}
