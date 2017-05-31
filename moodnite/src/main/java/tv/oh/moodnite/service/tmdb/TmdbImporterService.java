package tv.oh.moodnite.service.tmdb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.TagFromSource;
import tv.oh.moodnite.service.TagSourceService;

@Service
public class TmdbImporterService {
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	@Autowired
	private TagSourceService tagSourceService;
	
	private Movie movie;
	
	@SuppressWarnings("unchecked")
	public Movie importMovie(String movieId) {
		Map<?, ?> movieDetails = tmdbMovieService.getMovieDetails(movieId);
		movie = new Movie();
		movie.setTmdbId(movieId);
		movie.setTitle((String) movieDetails.get("title"));
		movie.setYear((String) movieDetails.get("year"));
		movie.setBackground((String) movieDetails.get("backdrop_path"));
		for(Map<?, ?> genre:((List<Map<String, String>>) movieDetails.get("genres"))) {
			movie.addTagFromSource(new TagFromSource(tagSourceService.getTmdbTagSource(), movie, (String) genre.get("name")));
		}
		return movie;
	}
}
