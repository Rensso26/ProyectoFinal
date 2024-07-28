package ec.edu.uce.interfaz.repository;

import ec.edu.uce.interfaz.state.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);
}
