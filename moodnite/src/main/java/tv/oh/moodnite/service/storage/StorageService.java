package tv.oh.moodnite.service.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {
	
	void init();
	
	void store(MultipartFile file);
	
	void store(MultipartFile file, String fileName);
	
	Stream<Path> loadAll();
	
	Path load(String fileName);
	
	Resource loadAsResource(String fileName);
	
	void deleteAll();
}
