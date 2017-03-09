package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import tv.oh.moodnite.domain.Rated;

public interface RateRepository extends GraphRepository<Rated> {

}
