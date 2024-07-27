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
    @DeleteMapping("toy/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        toyService.delete(name);
        return ResponseEntity.noContent().build();
    }



}
