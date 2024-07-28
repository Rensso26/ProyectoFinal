package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.Interfaces.ControlleableId;
import ec.edu.uce.interfaz.service.PeticionService;
import ec.edu.uce.interfaz.state.Peticion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fabric")
public class PeticionController implements ControlleableId<Peticion>{

    @Autowired
    private PeticionService peticionService;


    @Override
    @PostMapping("/peticion/")
    public ResponseEntity<Peticion> save(@RequestBody Peticion object) {
        Peticion savedPeticion = peticionService.save(object);
        return ResponseEntity.ok(savedPeticion);
    }

    @Override
    @GetMapping("/peticion/{id}")
    public ResponseEntity<Peticion> findById(@PathVariable int id) {
        Peticion peticion = peticionService.findById((long) id);
        if (peticion != null) {
            return ResponseEntity.ok(peticion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping("/peticion/")
    public ResponseEntity<List<Peticion>> getAll() {
        List<Peticion> peticiones = peticionService.findAll();
        return ResponseEntity.ok(peticiones);
    }


    @Override
    @PutMapping("/peticion/{id}")
    public ResponseEntity<Peticion> update(@PathVariable int id, @RequestBody Peticion object) {
        Peticion updatedPeticion = peticionService.update((long) id, object);
        if (updatedPeticion != null) {
            return ResponseEntity.ok(updatedPeticion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/peticion/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        peticionService.delete((long) id);
        return ResponseEntity.noContent().build();
    }
}
