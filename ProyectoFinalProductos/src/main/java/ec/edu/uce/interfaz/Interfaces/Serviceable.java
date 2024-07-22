package ec.edu.uce.interfaz.Interfaces;

import java.util.List;

public interface Serviceable {

    Object findByName(String name);
    List<Object> findAll();
    Object save(Object object);
    Object findById(Long id);
    void delete(String name);
    Object update(String name, Object object);
    Object update(Long id, Object object);

}
