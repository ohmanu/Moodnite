package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import tv.oh.moodnite.domain.Movie;

public interface MovieRepository extends GraphRepository<Movie> {

}
