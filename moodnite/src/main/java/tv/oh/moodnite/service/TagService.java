package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.repository.TagRepository;

@Service
public class TagService {
	@Autowired
	TagRepository tagRepo;
	
	public void tags() {
		tagRepo.findAll();
	}

}
