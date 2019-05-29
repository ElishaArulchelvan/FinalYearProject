package elisha.fyp.project.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import elisha.fyp.project.entity.User;

public interface UserService extends UserDetailsService{

	void save(User user);
	User findByEmail(String email);
	User findByFirstName(String firstName);
	List<User> findAll();
	void delete(Integer id);
	User findOne(Integer id);
	void updateUser(User user);
}
