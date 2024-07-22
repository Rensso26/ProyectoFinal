package ec.edu.uce.interfaz.service;
import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.state.Toy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class ToyService implements Serviceable {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Object save (Object object) {
        Toy existingToy = findByName(((Toy) object).getName());
        if(existingToy != null) {
            ((Toy) object).setId(existingToy.getId());
            existingToy.setName(((Toy) object).getName());
            existingToy.setPrice(((Toy) object).getPrice());
            existingToy.setCategory(((Toy) object).getCategory());
            existingToy.setCreationForm(((Toy) object).getCreationForm());
            existingToy.setParts(((Toy) object).getParts());
            existingToy.setColor(((Toy) object).getColor());
            return entityManager.merge(existingToy);
        }else {
            entityManager.persist(object);
            return (object);
        }
    }

    @Override
    public Toy findByName(String name) {
        try{
            return entityManager.createQuery("SELECT g FROM Toy g WHERE g.name = :name", Toy.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Object> findAll() {
        return entityManager.createQuery("SELECT g FROM Toy g").getResultList();
    }

    @Override
    public Toy findById(Long id) {
        return entityManager.find(Toy.class, id);
    }

    @Override
    public void delete(Long id) {
        Toy toy = findById(id);
        if (toy != null) {
            entityManager.remove(toy);
        }
    }

    @Override
    public Object update(String name, Object object) {
        object = new Toy();
         Toy existingToy = findByName(name);
            if(existingToy != null) {
                existingToy.setId(((ec.edu.uce.interfaz.state.Toy) object).getId());
                existingToy.setName(((ec.edu.uce.interfaz.state.Toy) object).getName());
                existingToy.setPrice(((ec.edu.uce.interfaz.state.Toy) object).getPrice());
                existingToy.setCategory(((ec.edu.uce.interfaz.state.Toy) object).getCategory());
                existingToy.setCreationForm( ((Toy) object).getCreationForm());
                existingToy.setParts(((ec.edu.uce.interfaz.state.Toy) object).getParts());
                existingToy.setColor(((ec.edu.uce.interfaz.state.Toy) object).getColor());
                return entityManager.merge(existingToy);
            }else {
                return null;
            }

    }

    @Override
    public Object update(Long id, Object object) {
        object = new Toy();
        Toy existingToy = findById(id);
        if(existingToy != null) {
            existingToy.setId(((ec.edu.uce.interfaz.state.Toy) object).getId());
            existingToy.setName(((ec.edu.uce.interfaz.state.Toy) object).getName());
            existingToy.setPrice(((ec.edu.uce.interfaz.state.Toy) object).getPrice());
            existingToy.setCategory(((ec.edu.uce.interfaz.state.Toy) object).getCategory());
            existingToy.setCreationForm( ((Toy) object).getCreationForm());
            existingToy.setParts(((ec.edu.uce.interfaz.state.Toy) object).getParts());
            existingToy.setColor(((ec.edu.uce.interfaz.state.Toy) object).getColor());
            return entityManager.merge(existingToy);
        }else {
            return null;
        }
    }
}
