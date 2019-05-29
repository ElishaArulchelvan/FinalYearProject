package elisha.fyp.project.service;

import java.util.Date;
import java.util.List;

import elisha.fyp.project.entity.Request;
import elisha.fyp.project.entity.Status;
import elisha.fyp.project.entity.User;

public interface RequestService {

	Request createRequest();
	Request findOne(Long id);
	List<Request> findByUser(User user);
	List<Request> findAll();
	//List<Request> findByUserId(Long id);
	void save(Request request);
	List<Request> findByUserId(Integer id);
	
	List<Request> findByStatus(Status status);
	Request findByDate(Date date);
	
	void delete(Long id);
	void deleteRequest(Request request);
	void updateRequest(Request request);
}
