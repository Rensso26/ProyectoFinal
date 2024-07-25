package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.ProductRequestService;
import ec.edu.uce.interfaz.service.UserService;
import ec.edu.uce.interfaz.service.ToyService;
import ec.edu.uce.interfaz.state.ProductRequest;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-requests")
public class ProductRequestController {

    @Autowired
    private ProductRequestService productRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private ToyService toyService;

    @PostMapping
    public ProductRequest createRequest(@RequestParam String username, @RequestParam String toyname) {
        User user = userService.findByName(username);
        Toy toy = toyService.findByName(toyname);
        return productRequestService.createRequest(user, toy);
    }

    @PutMapping("/approve/{id}")
    public void approveRequest(@PathVariable Long id) {
        productRequestService.approveRequest(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectRequest(@PathVariable Long id) {
        productRequestService.rejectRequest(id);
    }
}
