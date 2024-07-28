package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.repository.CategoryRepository;
import ec.edu.uce.interfaz.repository.ToyRepository;
import ec.edu.uce.interfaz.state.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements Serviceable<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ToyRepository toyRepository;

    @Override
    @Transactional
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category save(Category category) {
        // Aquí puedes hacer validaciones o ajustes adicionales si es necesario
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(String name) {

    }

    @Override
    @Transactional
    public Category update(String name, Category category) {
        Category existingCategory = findByName(name);

        // Actualiza los campos necesarios
        existingCategory.setName(category.getName());


        return categoryRepository.save(existingCategory);
    }


}