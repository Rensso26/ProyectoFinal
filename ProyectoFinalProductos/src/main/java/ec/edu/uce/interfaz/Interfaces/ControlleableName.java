package ec.edu.uce.interfaz.Interfaces;
import ec.edu.uce.interfaz.state.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControlleableName<T> {

    @PostMapping
    ResponseEntity<T> save(@RequestBody T object);

    @GetMapping("/{name}")
    ResponseEntity<T> findByName(@PathVariable String name);

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll();

    @PutMapping("/{name}")
    ResponseEntity<T> update(@PathVariable String name, @RequestBody T object);

    @DeleteMapping("/{name}")
    ResponseEntity<Void> delete(@PathVariable String name);
}