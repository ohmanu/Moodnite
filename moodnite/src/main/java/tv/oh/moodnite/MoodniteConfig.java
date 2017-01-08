package tv.oh.moodnite;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "tv.oh.moodnite.repository")
@EnableTransactionManagement
public class MoodniteConfig extends Neo4jConfiguration{
	@Bean
	public SessionFactory getSessionFactory() {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "info");
		return new SessionFactory("tv.oh.moodnite.domain");
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {
		return super.getSession();
	}
}
