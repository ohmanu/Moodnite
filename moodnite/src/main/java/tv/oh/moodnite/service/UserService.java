package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public User loginUser(String name, String password) {
		User user = userRepo.findByName(name);
		System.out.println("psw1: " + password + " psw2: " + user.getPassword());
		if(user != null && user.getPassword().equals(password))
			return user;
		
		return null;
	}
	
	public User signInUser(User user) {
		userRepo.save(user);
		
		return user;
	}
}
