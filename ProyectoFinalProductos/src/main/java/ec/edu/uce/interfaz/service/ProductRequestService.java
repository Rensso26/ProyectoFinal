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


    public ProductRequest createRequest(User user, Toy toy) {
        ProductRequest request = new ProductRequest();
        request.setUser(user);
        request.setToy(toy);
        request.setStatus("PENDING");
        return productRequestRepository.save(request);
    }


    public ProductRequest findById(Long id) {
        return productRequestRepository.findById(id).orElse(null);
    }


    public ProductRequest update(ProductRequest request) {
        return productRequestRepository.save(request);
    }


    public List<ProductRequest> getPendingRequests() {
        return productRequestRepository.findByStatus("PENDING");
    }


    public void approveRequest(Long id) {
        ProductRequest request = findById(id);
        if (request != null) {
            request.setStatus("APPROVED");
            update(request);
        }
    }


    public void rejectRequest(Long id) {
        ProductRequest request = findById(id);
        if (request != null) {
            request.setStatus("REJECTED");
            update(request);
        }
    }
}
