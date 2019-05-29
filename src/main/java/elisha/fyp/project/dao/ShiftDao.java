package elisha.fyp.project.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

import elisha.fyp.project.entity.Shift;
import elisha.fyp.project.entity.User;

@Repository
public interface ShiftDao extends JpaRepository<Shift, Long> {
	
	List<Shift> findAll();

	Set<Shift> findByUsersShifts(User user);

	Shift findById(int id);
	
	//List<Shift> findByDate(Date date);
	
	@Query("from Shift e where not(e.end < :from and e.start > :to)")
	public List<Shift> findBetween(@Param("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
	
	//@Query("from Shift e where e.start > :from and e.end < :to")
	//public List<Shift> findBetween(@Param("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
	
	Shift findByStart(LocalDateTime start);
	Shift findByEnd(LocalDateTime end);
	
	
	
}
