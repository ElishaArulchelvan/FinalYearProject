package elisha.fyp.project.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import elisha.fyp.project.dao.ShiftDao;
import elisha.fyp.project.entity.Shift;
import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.ShiftService;

@Service
@Transactional
public class ShiftServiceImpl implements ShiftService {
	
	@Autowired
	private ShiftDao shiftDao;

	@Override
	public List<Shift> findAll() {
		
		return shiftDao.findAll();
	}

	@Override
	public void save(Shift shift) {
		
		shiftDao.save(shift);
	}

	@Override
	public Set<Shift> findByUsersShifts(User user) {
		
		return shiftDao.findByUsersShifts(user);
	}

	@Override
	public Shift findOne(int id) {
		
		return shiftDao.findById(id);
	}

	/*@Override
	public List<Shift> findByDate(Date date) {
		
		return shiftDao.findByDate(date);
	}*/

	@Override
	public List<Shift> findBetween(LocalDateTime start, LocalDateTime end) {
		
		return shiftDao.findBetween(start, end);
	}

	@Override
	public Shift findByStart(LocalDateTime start) {
		
		return shiftDao.findByStart(start);
	}

	@Override
	public Shift findByEnd(LocalDateTime end) {
		
		return shiftDao.findByEnd(end);
	}

	@Override
	public void delete(Long id) {
		
		shiftDao.delete(id);
		
	}

	@Override
	public void deleteShift(Shift shift) {
		
		shiftDao.delete(shift);
		
	}

	
	/*@Override
	public void addUserToShift(User user, Shift shift) {
		
		Set<User> usersShifts = shift.getUsersShifts();
		usersShifts.add(user);
		shift.setUsersShifts((Set<User>)usersShifts);
		shiftDao.addUserToShift(shift, user);
		
	}*/



	

	/*@Override
	public List<Shift> findByUserId(Long id) {
		
		return shiftDao.findByUserId(id);
	}*/

}

