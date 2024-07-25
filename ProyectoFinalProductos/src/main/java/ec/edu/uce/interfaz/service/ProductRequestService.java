package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.repository.ProductRequestRepository;
import ec.edu.uce.interfaz.state.CreationForm;
import ec.edu.uce.interfaz.state.ProductRequest;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class ProductRequestService {

    @Autowired
    private ProductRequestRepository productRequestRepository;

    @Autowired
    private CreationFormService creationFormService;

    public ProductRequest createRequest(User user, Toy toy) {
        ProductRequest request = new ProductRequest();
        request.setUser(user);
        request.setToy(toy);
        request.setStatus("PENDING");
        request.setRequestTime(LocalDateTime.now());
        return productRequestRepository.save(request);
    }

    public void approveRequest(Long id) {
        Optional<ProductRequest> requestOpt = productRequestRepository.findById(id);
        if (requestOpt.isPresent()) {
            ProductRequest request = requestOpt.get();
            CreationForm creationForm = request.getToy().getCreationForm();
            double totalProductionTime = creationForm.getTimeEnsable() + creationForm.getTimePinter() + creationForm.getTimePackaging();
            request.setStatus("APPROVED");
            request.setCompletionTime(LocalDateTime.now().plusSeconds((long) totalProductionTime));
            productRequestRepository.save(request);

            // Emular tiempo de producción
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    completeRequest(request.getId());
                }
            }, (long) totalProductionTime * 1000);
        }
    }

    public void rejectRequest(Long id) {
        Optional<ProductRequest> requestOpt = productRequestRepository.findById(id);
        if (requestOpt.isPresent()) {
            ProductRequest request = requestOpt.get();
            request.setStatus("REJECTED");
            productRequestRepository.save(request);
        }
    }

    private void completeRequest(Long id) {
        Optional<ProductRequest> requestOpt = productRequestRepository.findById(id);
        if (requestOpt.isPresent()) {
            ProductRequest request = requestOpt.get();
            request.setStatus("COMPLETED");
            productRequestRepository.save(request);
        }
    }
}
