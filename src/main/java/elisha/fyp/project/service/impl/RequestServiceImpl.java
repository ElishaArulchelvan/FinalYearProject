package elisha.fyp.project.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elisha.fyp.project.dao.RequestDao;
import elisha.fyp.project.entity.Request;
import elisha.fyp.project.entity.Status;
import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestDao requestDao;

	@Override
	public Request createRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request findOne(Long id) {
		
		return requestDao.findOne(id);
	}

	@Override
	public List<Request> findByUser(User user) {
		
		return requestDao.findByUser(user);
	}

	@Override
	public List<Request> findAll() {
		
		return requestDao.findAll();
	}

	@Override
	public void save(Request request) {
		
		requestDao.save(request);
		
	}

	
	public List<Request> findByUserId(Integer id) {
		
		return requestDao.findByUserId(id);
	}

	@Override
	public void delete(Long id) {
		
		requestDao.delete(id);
	}

	@Override
	public void deleteRequest(Request request) {
		
		requestDao.delete(request);
		
	}
	
	public void updateRequest(Request request) {
		
		Request r = requestDao.findOne(request.getId());
		
		/*if(r!=null)
		{
			r.setId(request.getId());
		}
		
		r.setDate(request.getDate());
		r.setNumDays(request.getNumDays());
		r.setReason(request.getReason());
		r.setUser(request.getUser());
		r.setStatus(request.getStatus());
		*/
		r.setStatus(request.getStatus());
		System.out.println("Request has been updated ");
		
	}

	@Override
	public List<Request> findByStatus(Status status) {
		
		return requestDao.findByStatus(status);
	}

	@Override
	public Request findByDate(Date date) {
		
		return requestDao.findByDate(date);
	}

	

}
