package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User loginUser(String name, String password) {
		User user = userRepo.findByName(name);
		
		if(user != null && user.getPassword().equals(password))
			return user;
		
		return null;
	}
	
	public User signInUser(User user) {
		return userRepo.save(user);
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public User rateMovie(User user, Rated rate) {
		user.getRatedList().add(rate);
		return userRepo.save(user);
	}
}
