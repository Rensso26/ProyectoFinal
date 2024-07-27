package ec.edu.uce.interfaz.Interfaces;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ControlleableName<T> {

    @PostMapping
    ResponseEntity<T> save(@RequestBody T object);

    @GetMapping("/{name}")
    ResponseEntity<T> findByName(@PathVariable String name);

    @PutMapping("/{name}")
    ResponseEntity<T> update(@PathVariable String name, @RequestBody T object);

    @DeleteMapping("/{name}")
    ResponseEntity<Void> delete(@PathVariable String name);
}