package ec.edu.uce.interfaz.Interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControlleableId<T> {

    @PostMapping
    ResponseEntity<T> save(@RequestBody T object);

    @GetMapping("/{id}")
    ResponseEntity<T> findById(@PathVariable int id);

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll();

    @PutMapping("/{id}")
    ResponseEntity<T> update(@PathVariable int id, @RequestBody T object);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id);
}