package elisha.fyp.project.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elisha.fyp.project.entity.Request;
import elisha.fyp.project.entity.Shift;
import elisha.fyp.project.entity.Status;
import elisha.fyp.project.entity.User;

@Repository
public interface RequestDao extends JpaRepository<Request, Long> {
	
	List<Request> findByUser(User user);
	List<Request> findByUserId(Integer id);
	
	void delete(Long id);
	
	List<Request> findByStatus(Status status);
	Request findByDate(Date date);



}
