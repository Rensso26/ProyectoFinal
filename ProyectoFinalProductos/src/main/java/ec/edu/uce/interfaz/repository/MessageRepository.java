package ec.edu.uce.interfaz.repository;

import ec.edu.uce.interfaz.state.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
