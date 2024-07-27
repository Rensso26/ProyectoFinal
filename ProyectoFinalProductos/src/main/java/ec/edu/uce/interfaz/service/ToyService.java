package ec.edu.uce.interfaz.service;
import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.repository.CategoryRepository;
import ec.edu.uce.interfaz.repository.CreationFormRepository;
import ec.edu.uce.interfaz.repository.ToyRepository;
import ec.edu.uce.interfaz.state.CreationForm;
import ec.edu.uce.interfaz.state.Toy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToyService implements Serviceable<Toy> {


    @Autowired
    private ToyRepository toyRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CreationFormRepository creationFormRepository;


    @Override
    public Toy findByName(String name) {
        return null;
    }

    @Override
    public List<Toy> findAll() {
        return List.of();
    }


    @Override
    @Transactional
    public Toy save(Toy toy) {
        if (!categoryRepository.existsById(toy.getCategory().getName())) {
            categoryRepository.save(toy.getCategory());
        }

        // Guarda el Toy
        Toy savedToy = toyRepository.save(toy);

        if (toy.getCreationForm() != null) {
            CreationForm creationForm = toy.getCreationForm();
            creationForm.setToy(savedToy);
            creationFormRepository.save(creationForm);
        }

        return savedToy;
    }

    @Transactional
    public List<Toy> findToysByCategory(String categoryName) {
        return toyRepository.findByCategoryName(categoryName);
    }


    @Override
    public void delete(String name) {

    }

    @Override
    public Toy update(String name, Toy toy) {
        return null;
    }
}