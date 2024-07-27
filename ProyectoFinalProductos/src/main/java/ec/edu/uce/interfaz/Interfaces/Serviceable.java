package ec.edu.uce.interfaz.Interfaces;

import java.util.List;

public interface Serviceable<T> {

    T findByName(String name);
    List<T> findAll();
    T save(T object);
    void delete(String name);
    T update(String name, T object);
}
