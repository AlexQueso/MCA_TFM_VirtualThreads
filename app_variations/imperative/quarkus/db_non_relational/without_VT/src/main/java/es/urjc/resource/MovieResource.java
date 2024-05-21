package es.urjc.resource;

import es.urjc.dto.MovieRequest;
import es.urjc.dto.MovieResponse;
import es.urjc.dto.RatingRequest;
import es.urjc.entity.Movie;
import es.urjc.repository.MovieRepository;
import io.quarkus.panache.common.Page;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static java.util.Objects.isNull;

@Path("/movies")
public class MovieResource {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

    private final MovieRepository movieRepository;

    public MovieResource(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GET
    @Path("/{movieId}")
    public Response getMovieById(@PathParam("movieId") String movieId) {
        return movieRepository.findByIdOptional(movieId)
                .map(this::convertToResponse)
                .map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @GET
    public List<MovieResponse> getAllMoviesByPage(@QueryParam("page") Optional<Integer> pageNumber) {
        Page page = Page.of(pageNumber.orElse(DEFAULT_PAGE_NUMBER), PAGE_SIZE);
        return movieRepository.findByPage(page).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @POST
    public MovieResponse saveMovie(MovieRequest movieRequest) {

        Movie movie = convertToEntity(movieRequest);
        movieRepository.persist(movie);
        return convertToResponse(movie);

    }

    @PATCH
    @Path("/{movieId}/rating")
    public Response updateRating(@PathParam("movieId") String movieId, RatingRequest ratingRequest) {
        movieRepository.update("rating = ?1 where id = ?2", ratingRequest.getValue(), movieId);
        return getMovieById(movieId);
    }

    @PUT
    @Path("/{movieId}")
    public Response updateMovie(@PathParam("movieId") String movieId, MovieRequest movieRequest) {
        return movieRepository.findByIdOptional(movieId)
                .map(movie -> {
                    updateData(movie, movieRequest);
                    movieRepository.persist(movie);
                    return convertToResponse(movie);
                })
                .map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{movieId}")
    public Response deleteMovie(@PathParam("movieId") String movieId) {
        boolean isDeleted = movieRepository.deleteById(new ObjectId(movieId));
        Response.ResponseBuilder responseBuilder = isDeleted ? Response.noContent() : Response.status(NOT_FOUND);
        return responseBuilder.build();
    }

    private MovieResponse convertToResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId().toString())
                .poster(movie.getPoster())
                .title(movie.getTitle())
                .releasedYear(movie.getReleasedYear())
                .certificate(movie.getCertificate())
                .runtime(movie.getRuntime())
                .genre(movie.getGenre())
                .rating(movie.getRating())
                .overview(movie.getOverview())
                .director(movie.getDirector())
                .build();
    }

    private Movie convertToEntity(MovieRequest movieRequest) {
        return Movie.builder()
                .poster(movieRequest.getPoster())
                .title(movieRequest.getTitle())
                .releasedYear(movieRequest.getReleasedYear())
                .certificate(movieRequest.getCertificate())
                .runtime(movieRequest.getRuntime())
                .genre(movieRequest.getGenre())
                .rating(movieRequest.getRating())
                .overview(movieRequest.getOverview())
                .director(movieRequest.getDirector())
                .build();
    }

    private void updateData(Movie movie, MovieRequest movieRequest) {
        movie.setTitle(isNull(movieRequest.getTitle()) ? movie.getTitle() : movieRequest.getTitle());
        movie.setPoster(isNull(movieRequest.getPoster()) ? movie.getPoster() : movieRequest.getPoster());
        movie.setReleasedYear(isNull(movieRequest.getReleasedYear()) ? movie.getReleasedYear() : movieRequest.getReleasedYear());
        movie.setCertificate(isNull(movieRequest.getCertificate()) ? movie.getCertificate() : movieRequest.getCertificate());
        movie.setRuntime(isNull(movieRequest.getRuntime()) ? movie.getRuntime() : movieRequest.getRuntime());
        movie.setGenre(isNull(movieRequest.getGenre()) ? movie.getGenre() : movieRequest.getGenre());
        movie.setRating(isNull(movieRequest.getRating()) ? movie.getRating() : movieRequest.getRating());
        movie.setOverview(isNull(movieRequest.getOverview()) ? movie.getOverview() : movieRequest.getOverview());
        movie.setDirector(isNull(movieRequest.getDirector()) ? movie.getDirector() : movieRequest.getDirector());
    }
}