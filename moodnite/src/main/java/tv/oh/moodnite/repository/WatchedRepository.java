package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import tv.oh.moodnite.domain.Watched;

public interface WatchedRepository extends GraphRepository<Watched> {

}
