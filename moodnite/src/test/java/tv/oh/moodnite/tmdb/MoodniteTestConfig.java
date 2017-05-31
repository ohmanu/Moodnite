package tv.oh.moodnite.tmdb;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("tv.oh.moodnite.repository")
@EnableTransactionManagement
@ComponentScan("tv.oh.moodnite")
public class MoodniteTestConfig extends Neo4jConfiguration {

	@Override
	public SessionFactory getSessionFactory() {
		return new SessionFactory("tv.oh.moodnite.domain");
	}

	@Override
    @Bean
    public Session getSession() throws Exception {
        return super.getSession();
    }
}
