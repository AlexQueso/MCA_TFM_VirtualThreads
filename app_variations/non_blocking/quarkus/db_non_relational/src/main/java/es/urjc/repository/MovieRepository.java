package es.urjc.repository;

import es.urjc.entity.Movie;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MovieRepository implements ReactivePanacheMongoRepository<Movie> {

    public Multi<Movie> findByPage(Page page) {
        return findAll().page(page).stream();
    }


}
