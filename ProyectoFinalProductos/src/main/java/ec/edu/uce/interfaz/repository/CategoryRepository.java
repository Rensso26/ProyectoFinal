package ec.edu.uce.interfaz.repository;

import ec.edu.uce.interfaz.state.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {
    // Métodos personalizados para operaciones con la entidad Category
    Mono<Category> findByName(String name);
    Mono<Void> deleteByName(String name);
}
