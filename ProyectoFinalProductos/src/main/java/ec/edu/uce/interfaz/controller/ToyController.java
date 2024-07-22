package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.Interfaces.Controlleable;
import ec.edu.uce.interfaz.service.ToyService;
import ec.edu.uce.interfaz.state.Toy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class ToyController implements Controlleable {

    private ToyService toyService = new ToyService();

    @Override
    @PostMapping("/toy/")
    public Object save(@RequestBody Object object) {
        return  toyService.save(object);
    }
    @Override
    @GetMapping("/toy/{name}")
    public Toy findByName(@PathVariable String name) {
        return toyService.findByName(name);
    }

    @Override
    @PutMapping("/toy/{name}")
    public Object update(@PathVariable String name, @RequestBody  Object object) {
        return  toyService.update(name, object);
    }

    @Override
    @DeleteMapping("toy/del/{id}")
    public void delete(@PathVariable String name) {
        toyService.delete(name);
    }



}
