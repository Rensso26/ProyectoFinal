package ec.edu.uce.interfaz.Interfaces;

import org.springframework.web.bind.annotation.*;

public interface ControlleableName {

    @PostMapping
    Object save(@RequestBody Object object);
    @GetMapping
    Object findByName(@PathVariable String name);
    @PutMapping
    Object update(@PathVariable String name, @RequestBody Object object);
    @DeleteMapping
    void delete(@PathVariable String name);
}
