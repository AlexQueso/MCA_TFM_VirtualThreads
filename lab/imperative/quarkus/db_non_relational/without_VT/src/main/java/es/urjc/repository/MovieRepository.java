package es.urjc.repository;

import es.urjc.entity.Movie;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepository implements PanacheMongoRepository<Movie> {

    public List<Movie> findByPage(Page page) {
        return findAll().page(page).list();
    }


    public Optional<Movie> findByIdOptional(String movieId) {
        return find("id", movieId).firstResultOptional();
    }
}
