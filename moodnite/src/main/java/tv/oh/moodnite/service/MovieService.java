package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.repository.MovieRepository;
import tv.oh.moodnite.service.tmdb.TmdbImporterService;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private TmdbImporterService tmdbImporterService;
	
	public Movie findByTmdbId(String tmdbId) {
		Movie movie = movieRepo.findByTmdbId(tmdbId);
		
		if(movie == null)
			return null;
		else
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
		Movie movie = movieRepo.findByTmdbId(tmdbId);
		
		if(movie == null)
			return movieRepo.save(tmdbImporterService.importMovie(tmdbId));
		
		return movie;
	}
}
