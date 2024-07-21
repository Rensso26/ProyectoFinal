package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.FactoryService;
import ec.edu.uce.interfaz.service.ToyService;
import ec.edu.uce.interfaz.service.UserService;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/fabric")
public class FabricController {

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private ToyService toyService;

    @Autowired
    private UserService userService;

    @PostMapping("/toy")
    public Toy saveToy(@RequestBody Toy toy) {
        return toyService.save(toy);
    }

    @GetMapping("/toy/{name}")
    public Toy findToyByName(@PathVariable String name) {
        return toyService.findByName(name).orElse(null);
    }

    @PutMapping("/toy/{name}")
    public Toy updateToy(@PathVariable String name, @RequestBody Toy toy) {
        return toyService.update(name, toy);
    }

    @DeleteMapping("/toy/{id}")
    public void deleteToy(@PathVariable int id) {
        toyService.delete(id);
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/user/{name}")
    public User findUserByName(@PathVariable String name) {
        return userService.findByName(name).orElse(null);
    }

    @PutMapping("/user/{name}")
    public User updateUser(@PathVariable String name, @RequestBody User user) {
        return userService.update(name, user);
    }

    @DeleteMapping("/user/{name}")
    public void deleteUser(@PathVariable String name) {
        userService.delete(name);
    }

    @PostMapping("/fabricate/{toyName}")
    public CompletableFuture<String> fabricarProducto(@PathVariable String toyName) {
        Toy toy = toyService.findByName(toyName).orElse(null);
        if (toy != null) {
            return factoryService.fabricarProducto(toy);
        } else {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.complete("Toy not found");
            return future;
        }
    }
}
