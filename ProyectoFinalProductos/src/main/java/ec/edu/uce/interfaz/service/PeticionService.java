package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.Interfaces.ServiceableId;
import ec.edu.uce.interfaz.repository.PeticionRepository;
import ec.edu.uce.interfaz.state.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeticionService implements ServiceableId<Peticion> {

    @Autowired
    private PeticionRepository peticionRepository;

    @Override
    public List<Peticion> findAll() {
        return peticionRepository.findAll();
    }

    @Override
    public Peticion save(Peticion peticion) {
        return peticionRepository.save(peticion);
    }

    @Override
    public Peticion findById(Long id) {
        int ids = id.intValue();
        return peticionRepository.findById(ids).orElse(null);
    }

    @Override
    public void delete(Long Id) {
        Peticion peticion = findById(Id);
        if(peticion != null) {
            peticionRepository.delete(peticion);
        }
    }

    @Override
    public Peticion update(Long id, Peticion peticion) {
        int ids = id.intValue();
        return peticionRepository.findById(ids).map(existingPeticion -> {
            existingPeticion.setCantidad(peticion.getCantidad());
            return peticionRepository.save(existingPeticion);
        }).orElse(null);
    }
}
