package ec.edu.uce.interfaz.repository;

import ec.edu.uce.interfaz.state.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeticionRepository extends JpaRepository<Peticion, Integer> {
}
