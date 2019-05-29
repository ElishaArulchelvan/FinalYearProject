package elisha.fyp.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import elisha.fyp.project.entity.Shift;
import elisha.fyp.project.entity.User;

public interface ShiftService {
	
	List<Shift> findAll();
	void save(Shift shift);
	Shift findOne(int id);
	
	//List<Shift> findByUser(User user);
	//List<Shift> findByUserId(Long id);
	Set<Shift> findByUsersShifts(User user);
	
	//List<Shift> findByDate(Date date);
	
	List<Shift> findBetween(LocalDateTime start, LocalDateTime end);
	
	Shift findByStart(LocalDateTime start);
	Shift findByEnd(LocalDateTime end);
	
	void delete(Long id);
	void deleteShift(Shift shift);
}
