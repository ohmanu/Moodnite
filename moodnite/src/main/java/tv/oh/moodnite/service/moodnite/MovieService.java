package tv.oh.moodnite.service.moodnite;

import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.repository.MovieRepository;
import tv.oh.moodnite.service.tmdb.TmdbImporterService;

@Service
public class MovieService {
	@Autowired
    private Session session;
	
	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private TmdbImporterService tmdbImporterService;
	
	public Movie findByTmdbId(String tmdbId) {
		Movie movie =  IteratorUtil.firstOrNull(findMovieByProperty("tmdbId", tmdbId).iterator());	
		
		return movie;
	}
	
	/**
	 * Se añade una película al grafo si esta no ha sido añadida previamente.
	 * 
	 * @param tmdbId
	 * @return El nodo de la película correspondiente al ID que se ha recibido como parámetro. 
	 */
	@Transactional
	public Movie addMovie(String tmdbId) {
		Movie movie = findByTmdbId(tmdbId);
		
		if(movie == null)
			return movieRepo.save(tmdbImporterService.importMovie(tmdbId));
		
		return movie;
	}
	
	public Iterable<Movie> findMovieByProperty(String propertyName, Object propertyValue) {
        return session.loadAll(Movie.class, new Filter(propertyName, propertyValue));
    }
}
