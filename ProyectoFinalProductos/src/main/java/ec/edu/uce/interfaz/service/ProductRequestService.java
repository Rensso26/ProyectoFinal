package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.repository.ProductRequestRepository;
import ec.edu.uce.interfaz.state.ProductRequest;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRequestService {

    @Autowired
    private ProductRequestRepository productRequestRepository;

    /**
     * Crea una nueva solicitud de producto.
     *
     * @param user El usuario que realiza la solicitud.
     * @param toy El juguete solicitado.
     * @return La solicitud de producto creada.
     */
    public ProductRequest createRequest(User user, Toy toy) {
        ProductRequest request = new ProductRequest();
        request.setUser(user);
        request.setToy(toy);
        request.setStatus("PENDING");
        return productRequestRepository.save(request);
    }

    /**
     * Encuentra una solicitud de producto por su ID.
     *
     * @param id El ID de la solicitud.
     * @return La solicitud de producto.
     */
    public ProductRequest findById(Long id) {
        return productRequestRepository.findById(id).orElse(null);
    }

    /**
     * Actualiza una solicitud de producto.
     *
     * @param request La solicitud de producto a actualizar.
     * @return La solicitud de producto actualizada.
     */
    public ProductRequest update(ProductRequest request) {
        return productRequestRepository.save(request);
    }

    /**
     * Obtiene todas las solicitudes pendientes.
     *
     * @return Una lista de todas las solicitudes pendientes.
     */
    public List<ProductRequest> getPendingRequests() {
        return productRequestRepository.findByStatus("PENDING");
    }

    /**
     * Aprueba una solicitud de producto.
     *
     * @param id El ID de la solicitud a aprobar.
     */
    public void approveRequest(Long id) {
        ProductRequest request = findById(id);
        if (request != null) {
            request.setStatus("APPROVED");
            update(request);
        }
    }

    /**
     * Rechaza una solicitud de producto.
     *
     * @param id El ID de la solicitud a rechazar.
     */
    public void rejectRequest(Long id) {
        ProductRequest request = findById(id);
        if (request != null) {
            request.setStatus("REJECTED");
            update(request);
        }
    }
}
