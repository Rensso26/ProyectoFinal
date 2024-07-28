package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.Interfaces.ProductRequestObserver;
import ec.edu.uce.interfaz.repository.ProductRequestRepository;
import ec.edu.uce.interfaz.state.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRequestService implements ProductRequestObserver {

    @Autowired
    private ProductRequestRepository productRequestRepository;

    public ProductRequest createRequest(ProductRequest request) {
        request.setStatus("PENDING");
        ProductRequest savedRequest = productRequestRepository.save(request);

        new Thread(() -> {
            try {
                // Emular el tiempo de fabricación
                Thread.sleep(request.getFabricationTime() * 1000);
                savedRequest.setStatus("COMPLETED");
                productRequestRepository.save(savedRequest);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                savedRequest.setStatus("FAILED");
                productRequestRepository.save(savedRequest);
            }
        }).start();

        return savedRequest;
    }

    @Override
    public void update(ProductRequest request) {
        // Lógica adicional si es necesario
    }

    public ProductRequest getRequestStatus(Long id) {
        return productRequestRepository.findById(id).orElse(null);
    }
}
