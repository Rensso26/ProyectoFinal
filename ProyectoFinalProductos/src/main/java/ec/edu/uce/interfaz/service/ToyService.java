package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.state.Toy;
import ec.edu.uce.interfaz.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToyService {

    @Autowired
    private ToyRepository toyRepository;

    public Toy save(Toy toy) {
        return toyRepository.save(toy);
    }

    public Optional<Toy> findByName(String name) {
        return toyRepository.findByName(name);
    }

    public Toy update(String name, Toy toy) {
        return toyRepository.save(toy);
    }

    public void delete(int id) {
        toyRepository.deleteById(id);
    }

    public List<Toy> findAll() {
        return toyRepository.findAll();
    }
}
