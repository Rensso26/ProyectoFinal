package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.ProductRequestService;
import ec.edu.uce.interfaz.state.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class ProductRequestController {

    @Autowired
    private ProductRequestService productRequestService;

    @PostMapping("/request")
    public ResponseEntity<ProductRequest> createRequest(@RequestBody ProductRequest request) {
        ProductRequest createdRequest = productRequestService.createRequest(request);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<ProductRequest> getRequestStatus(@PathVariable Long id) {
        ProductRequest request = productRequestService.getRequestStatus(id);
        if (request != null) {
            return ResponseEntity.ok(request);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
