package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import tv.oh.moodnite.domain.TagSource;

public interface TagSourceRepository extends GraphRepository<TagSource> {

	@Query("MATCH (tagSource:TagSource) WHERE tagSource.sourceName = {sourceName} RETURN tagSource")
	TagSource findBySourceName(@Param("sourceName") String sourceName);
}
