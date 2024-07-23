package ec.edu.uce.interfaz.repository;

import ec.edu.uce.interfaz.state.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
