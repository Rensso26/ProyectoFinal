package ec.edu.uce.interfaz.repository;

import ec.edu.uce.interfaz.state.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {
    List<ProductRequest> findByStatus(String status);
}
