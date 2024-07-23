package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.Interfaces.ControlleableName;
import ec.edu.uce.interfaz.service.CategoryService;
import ec.edu.uce.interfaz.state.Category;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class CategoryController implements ControlleableName {

    private CategoryService categoryService = new CategoryService();

    @Override
    @PostMapping("/category/")
    public Object save(@RequestBody Object object) {
        return categoryService.save(object);
    }

    @Override
    @GetMapping("/category/{name}")
    public Category findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @Override
    @PutMapping("/category/{name}")
    public Object update(@PathVariable String name, @RequestBody Object object) {
        return categoryService.update(name, object);
    }

    @Override
    @DeleteMapping("/category/del/{name}")
    public void delete(@PathVariable String name) {
        categoryService.delete(name);
    }
}
