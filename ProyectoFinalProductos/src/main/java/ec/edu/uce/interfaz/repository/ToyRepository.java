package ec.edu.uce.interfaz.repository;

import ec.edu.uce.interfaz.state.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Integer> {
        List<Toy> findByCategoryName(String categoryName);
}
