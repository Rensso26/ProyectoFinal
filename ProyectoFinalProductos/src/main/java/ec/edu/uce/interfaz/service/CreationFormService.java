package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.Interfaces.ServiceableId;
import ec.edu.uce.interfaz.state.CreationForm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreationFormService implements ServiceableId {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Object save(Object object) {
        CreationForm existingCreationForm = findById(((CreationForm) object).getIdToy());
        if (existingCreationForm != null) {
            existingCreationForm.setTimeEnsable(((CreationForm) object).getTimeEnsable());
            existingCreationForm.setTimePinter(((CreationForm) object).getTimePinter());
            existingCreationForm.setTimePackaging(((CreationForm) object).getTimePackaging());
            existingCreationForm.setToy(((CreationForm) object).getToy());
            return entityManager.merge(existingCreationForm);
        } else {
            entityManager.persist(object);
            return object;
        }
    }


    @Override
    public List<Object> findAll() {
        return entityManager.createQuery("SELECT c FROM CreationForm c").getResultList();
    }

    @Override
    public CreationForm findById(Long id) {
        return entityManager.find(CreationForm.class, id);
    }

    @Override
    public void delete(Long id) {
        CreationForm creationForm = findById(id);
        if (creationForm != null) {
            entityManager.remove(creationForm);
        }
    }

    @Override
    public Object update(Long id, Object object) {
        CreationForm existingCreationForm = findById(id);
        if (existingCreationForm != null) {
            existingCreationForm.setTimeEnsable(((CreationForm) object).getTimeEnsable());
            existingCreationForm.setTimePinter(((CreationForm) object).getTimePinter());
            existingCreationForm.setTimePackaging(((CreationForm) object).getTimePackaging());
            existingCreationForm.setToy(((CreationForm) object).getToy());
            return entityManager.merge(existingCreationForm);
        } else {
            return null;
        }
    }
}
