package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import tv.oh.moodnite.domain.Movie;

public interface MovieRepository extends GraphRepository<Movie> {
	@Query("MATCH (movie:Movie) WHERE movie.tmdbId = {tmdbId} RETURN movie")
	Movie findByTmdbId(@Param("tmdbId") String tmdbId);
}
