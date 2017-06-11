package tv.oh.moodnite.service.moodnite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.TagSource;
import tv.oh.moodnite.repository.TagSourceRepository;

@Service
public class TagSourceService {
	private static String TMDB_TAG_SOURCE_NAME = "TMDB";
	
	@Autowired
	private TagSourceRepository tagSourceRepo;
	
	public TagSource getTmdbTagSource() {
		return findBySourceName(TMDB_TAG_SOURCE_NAME);
	}
	
	private TagSource findBySourceName(String sourceName) {
		return tagSourceRepo.findBySourceName(sourceName);
	}
}
