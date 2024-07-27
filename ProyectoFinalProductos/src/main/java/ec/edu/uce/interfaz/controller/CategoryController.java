package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.Interfaces.ControlleableName;
import ec.edu.uce.interfaz.service.CategoryService;
import ec.edu.uce.interfaz.state.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fabric")
public class CategoryController implements ControlleableName<Category> {

    @Autowired
    private CategoryService categoryService ;


    @Override
    @PostMapping("/category/")
    public ResponseEntity<Category> save(@RequestBody Category category) {
      Category savedCategory = categoryService.save(category) ;
      return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si no hay categorías
        }
        return ResponseEntity.ok(categories);
    }


    @Override
    @GetMapping("/category/{name}")
    public ResponseEntity<Category> findByName(@PathVariable String name) {
        Category category = categoryService.findByName(name);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Category> update(String name, Category object) {
        return null;
    }

    @Override
    @DeleteMapping("/category/del/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        categoryService.delete(name);
        return null;
    }
}
