package ec.edu.uce.interfaz.controller;
import ec.edu.uce.interfaz.Interfaces.ControlleableName;
import ec.edu.uce.interfaz.service.ToyService;
import ec.edu.uce.interfaz.state.Toy;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fabric")
public class ToyController implements ControlleableName<Toy> {

    @Autowired
    private ToyService toyService ;


    @Override
    @PostMapping("/toy/")
    public ResponseEntity<Toy> save(@RequestBody Toy toy) {
        Toy savedToy = toyService.save(toy);
        return ResponseEntity.ok(savedToy);
    }

    @Override
    @GetMapping("/toy/{name}")
    public ResponseEntity<Toy> findByName(@PathVariable String name) {
        Toy toy = toyService.findByName(name);
        if (toy != null) {
            return ResponseEntity.ok(toy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/toyid/{id}")
    public ResponseEntity<Toy> findById(@PathVariable Integer id) {
        Toy toy = toyService.findById(id);
        if (toy != null) {
            return ResponseEntity.ok(toy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Toy>> getAll() {
        List<Toy>toys = toyService.findAll();
        if (toys.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si no hay categorías
        }
        return ResponseEntity.ok(toys);
    }

    @GetMapping("/toy/category/{name}")
    public ResponseEntity<List<Toy>> getToysByCategory(@PathVariable String name) {
        List<Toy> toys = toyService.findToysByCategory(name);
        return new ResponseEntity<>(toys, HttpStatus.OK);
    }


    @Override
    @PutMapping("/toy/{name}")
    public ResponseEntity<Toy> update(@PathVariable String name, @RequestBody Toy toy) {
        try {
            Toy updatedToy = toyService.update(name, toy);
            return ResponseEntity.ok(updatedToy);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("toy/del/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        toyService.delete(name);
        return ResponseEntity.noContent().build();
    }



}
