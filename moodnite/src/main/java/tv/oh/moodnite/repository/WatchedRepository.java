package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import tv.oh.moodnite.domain.Watched;

public interface WatchedRepository extends GraphRepository<Watched> {
	
	@Query("MATCH (u:User)-[r:WATCHED]-(m:Movie) WHERE ID(u) = {userId} AND m.tmdbId = {tmdbId} RETURN r")
	Watched findUserMovieWatch(@Param("userId") Long userId, @Param("tmdbId") String tmdbId);
}
