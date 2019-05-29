package elisha.fyp.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import elisha.fyp.project.dao.UserDao;
import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("%s doesn't exist in database!", email));

		}
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				authorities
				);
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}


	public void delete(Integer id) {
		userDao.delete(id);
	}

	public User findOne(Integer id) {
		return userDao.findOne(id);
	}

	@Override
	public User findByFirstName(String firstName) {
		
		return userDao.findByFirstName(firstName);
	}


	public void updateUser(User user) {
		
		User u = userDao.findByFirstName(user.getFirstName());
		
		if(u!=null)
		{
			u.setId(user.getId());
		}
		
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail());
		
		
	}


}
