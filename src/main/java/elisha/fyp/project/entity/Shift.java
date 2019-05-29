package elisha.fyp.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import elisha.fyp.project.converter.LocalDateTimeToStringConverter;

@Entity
@Table(name = "Shift")
public class Shift {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shiftId")
	int id;
	
	@Column(name = "start", nullable=false)
	private LocalDateTime start;
	
	@Column(name = "end", nullable = false)
	private LocalDateTime end;
	
	//either 4 or 8 hour shift
	/*@Column(name = "type", nullable = false)
	private String type;*/
	
	/*@Column(name = "date", nullable = false)
	private LocalDate date;
	*/
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Shift_User", 
             joinColumns = { @JoinColumn(name = "shiftId") }, 
             inverseJoinColumns = { @JoinColumn(name = "userId") })
	@JsonManagedReference
    private Set<User> usersShifts = new HashSet<User>();
	
	
	
	
	
	public Shift()
	{
		
	}
	
	public Shift(LocalDateTime start, LocalDateTime end )
	{
		this.start = start;
		this.end = end;
		//this.type = type;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	/*public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}*/

	/*public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}*/

	public Set<User> getUsersShifts() {
		return usersShifts;
	}

	public void setUsersShifts(Set<User> usersShifts) {
		this.usersShifts = usersShifts;
	}

	/*public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}*/
	
	public void addUser(User u) {
		usersShifts.add(u);
		u.addShift(this);
	}
	
	
	
}

	
//insert into User (Active, Email, FirstName, LastName, Password, role) values (1, 'admin@dit.ie', 'Admin', 'Admin', 'admin', 'ADMIN');
//my json server

//insert into shift(end, start) values('2019-04-09T16:00:00', '2019-04-09T14:00:00');
	
//http://localhost:8080/Test/google/events?to=2019-03-01T00:00:00.000Z&from=2019-03-20T00:00:00.000Z


