package elisha.fyp.project.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elisha.fyp.project.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	//Repositiory reads from database
	//so it finds the user by email

    User findByEmail(String email);
    List<User> findAll();
    User findByFirstName(String firstName);

}