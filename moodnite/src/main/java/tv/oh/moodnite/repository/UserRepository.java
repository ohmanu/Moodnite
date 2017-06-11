package tv.oh.moodnite.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import tv.oh.moodnite.domain.User;

public interface UserRepository extends GraphRepository<User> {
	
	@Query("MATCH (user:User) where user.name =~ {regularExpression} return user")
    List<User> findByNameLike(@Param("regularExpression") String regularExpression);
	
	@Query("MATCH (user:User {name:{name}})-[:FOLLOWS*1..3]->(f) RETURN f")
	List<User> findSocialNet(@Param("name") String name);
}
