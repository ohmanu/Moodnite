package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import tv.oh.moodnite.domain.Rated;

public interface RateRepository extends GraphRepository<Rated> {
	
	@Query("MATCH (u:User)-[r:RATED]-(m:Movie) WHERE ID(u) = {userId} AND m.tmdbId = {tmdbId} RETURN r")
	Rated findUserMovieRate(@Param("userId") Long userId, @Param("tmdbId") String tmdbId);
}
