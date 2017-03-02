package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import tv.oh.moodnite.domain.User;

public interface UserRepository extends GraphRepository<User>{

	@Query("MATCH (user:User) WHERE user.login= {login} RETURN user")
	User findByLogin(@Param("login") String login);
}
