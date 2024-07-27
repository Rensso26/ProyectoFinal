package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.ReactiveProductRequestService;
import ec.edu.uce.interfaz.service.UserService;
import ec.edu.uce.interfaz.service.ToyService;
import ec.edu.uce.interfaz.state.ProductRequest;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product-requests")
public class ProductRequestController {

    @Autowired
    private ReactiveProductRequestService reactiveProductRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private ToyService toyService;

    /**
     * Crea una nueva solicitud de producto.
     *
     * @param username El nombre de usuario que realiza la solicitud.
     * @param toyname El nombre del juguete solicitado.
     * @return Un Mono que emite la solicitud de producto creada.
     */
    @PostMapping
    public Mono<ProductRequest> createRequest(@RequestParam String username, @RequestParam String toyname) {
        User user = userService.findByName(username);
        Toy toy = toyService.findByName(toyname);
        return reactiveProductRequestService.createRequest(user, toy);
    }

    /**
     * Aprueba una solicitud de producto.
     *
     * @param id El ID de la solicitud a aprobar.
     * @return Un Mono que completa cuando la solicitud ha sido aprobada.
     */
    @PutMapping("/approve/{id}")
    public Mono<Void> approveRequest(@PathVariable Long id) {
        return reactiveProductRequestService.processRequest(id);
    }

    /**
     * Obtiene todas las solicitudes pendientes.
     *
     * @return Un Flux que emite todas las solicitudes pendientes.
     */
    @GetMapping("/pending")
    public Flux<ProductRequest> getPendingRequests() {
        return reactiveProductRequestService.getPendingRequests();
    }
}
