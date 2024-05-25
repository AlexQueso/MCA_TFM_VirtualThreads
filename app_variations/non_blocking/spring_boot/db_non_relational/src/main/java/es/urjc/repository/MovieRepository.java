package es.urjc.repository;

import es.urjc.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveCrudRepository<Movie, String> {
    Flux<Movie> findAllBy(Pageable pageable);

}
