package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.ToyService;
import ec.edu.uce.interfaz.service.UserService;
import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.state.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class FabricController {

    private ToyService toyService;


    @PostMapping
    public Toy save(@RequestBody Toy toy) {
        return (Toy) toyService.save(toy);
    }

    @GetMapping("toy/{name}")
    public Toy findByName(@PathVariable String name) {
        return toyService.findByName(name);
    }

    @PutMapping("/toy/{name}")
    public Toy update(@PathVariable String name, @RequestBody Toy toy) {
        return (Toy) toyService.update(name, toy);
    }
    @DeleteMapping("toy/del/{id}")
    public void delete(@PathVariable Long id) {
        toyService.delete(id);
    }


}
