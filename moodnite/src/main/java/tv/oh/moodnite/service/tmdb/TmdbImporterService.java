package tv.oh.moodnite.service.tmdb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.TagFromSource;
import tv.oh.moodnite.service.moodnite.TagSourceService;

@Service
public class TmdbImporterService {
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	@Autowired
	private TagSourceService tagSourceService;
	
	private Movie movie;
	
	public Movie importMovie(String movieId) {
		Map<?, ?> movieDetails = tmdbMovieService.getMovieDetails(movieId);
		this.movie = new Movie();
		this.movie.setTmdbId(movieId);
		this.movie.setTitle((String) movieDetails.get("title"));
		this.movie.setYear((String) movieDetails.get("release_date").toString().substring(0, 4));
		this.movie.setBackground((String) movieDetails.get("backdrop_path"));
		loadTagsFromTmdb(movieDetails);
				
		return movie;
	}
	
	@SuppressWarnings("unchecked")
	private void loadTagsFromTmdb(Map<?, ?> movieDetails) {
		for(Map<?, ?> genre:((List<Map<String, String>>) movieDetails.get("genres"))) {
			this.movie.addTagFromSource(new TagFromSource(tagSourceService.getTmdbTagSource(), movie, (String) genre.get("name")));
		}
	}
}
