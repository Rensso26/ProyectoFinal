package ec.edu.uce.interfaz.Interfaces;

import org.springframework.web.bind.annotation.*;

public interface ControlleableId {

        @PostMapping
        Object save(@RequestBody Object object);
        @GetMapping
        Object findById(@PathVariable Long id);
        @PutMapping
        Object update(@PathVariable Long id, @RequestBody Object object);
        @DeleteMapping
        void delete(@PathVariable Long id);
}
