package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.FactoryService;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class FabricController {

    @Autowired
    private FactoryService service;

    @PostMapping
    public Toy save(@RequestBody Toy toy) {
        return service.save(toy);
    }

    @GetMapping("toy/{name}")
    public Toy findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PutMapping("/toy/{name}")
    public Toy update(@PathVariable String name, @RequestBody Toy toy) {
        return service.update(name, toy);
    }
    @DeleteMapping("toy/del/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping("toy/{name}")
    public User findByName(@PathVariable User name) {
        return service.findByName(name);
    }

    @PutMapping("/toy/{name}")
    public User update(@PathVariable String name, @RequestBody User user) {
        return service.update(name, user);
    }
    @DeleteMapping("toy/del/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }


}
