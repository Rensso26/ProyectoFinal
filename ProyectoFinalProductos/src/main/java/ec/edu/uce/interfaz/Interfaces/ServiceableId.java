package ec.edu.uce.interfaz.Interfaces;

import java.util.List;

public interface ServiceableId {

    List<Object> findAll();
    Object save(Object object);
    Object findById(Long id);
    void delete(Long Id);
    Object update(Long id, Object object);

}
