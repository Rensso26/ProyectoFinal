package ec.edu.uce.interfaz.Interfaces;

import java.util.List;

public interface ServiceableId<T> {

    List<T> findAll();
    T save(T object);
    T findById(Long id);
    void delete(Long Id);
    T update(Long id, T object);

}
