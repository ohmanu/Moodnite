package tv.oh.moodnite.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import tv.oh.moodnite.domain.User;

public interface UserRepository extends GraphRepository<User>{

}
