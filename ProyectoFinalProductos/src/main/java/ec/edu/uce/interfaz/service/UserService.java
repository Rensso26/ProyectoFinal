package ec.edu.uce.interfaz.service;
import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.state.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements Serviceable {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Object save(Object object) {
        User existingUser = findByName(((User) object).getName());
        if (existingUser != null) {
            existingUser.setPassword(((User) object).getPassword());
            return entityManager.merge(existingUser);
        } else {
            entityManager.persist(object);
            return object;
        }
    }

    @Override
    public User findByName(String name) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Object> findAll() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }


    @Override
    public void delete(String name) {
        // Since User does not have an id, this method is not applicable.
    }

    @Override
    public Object update(String name, Object object) {
        User existingUser = findByName(name);
        if (existingUser != null) {
            existingUser.setPassword(((User) object).getPassword());
            return entityManager.merge(existingUser);
        } else {
            return null;
        }
    }

}
