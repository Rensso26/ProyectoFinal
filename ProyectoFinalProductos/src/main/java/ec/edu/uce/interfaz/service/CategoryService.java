package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.state.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class CategoryService implements Serviceable {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Object save(Object object) {
        Category existingCategory = findByName(((Category) object).getName());
        if (existingCategory != null) {
            existingCategory.setToys(((Category) object).getToys());
            existingCategory.setName(((Category) object).getName());
            return entityManager.merge(existingCategory);
        } else {
            entityManager.persist(object);
            return object;
        }
    }

    @Override
    public Category findByName(String name) {
        try {
            return entityManager.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Object> findAll() {
        return entityManager.createQuery("SELECT c FROM Category c").getResultList();
    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public Object update(String name, Object object) {
        Category existingCategory = findByName(name);
        if (existingCategory != null) {
            existingCategory.setToys(((Category) object).getToys());
            existingCategory.setName(((Category) object).getName());
            return entityManager.merge(existingCategory);
        } else {
            return null;
        }
    }

    @Override
    public Object update(Long id, Object object) {
        return null;
    }
}
