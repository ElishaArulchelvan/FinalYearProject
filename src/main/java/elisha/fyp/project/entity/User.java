package elisha.fyp.project.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//https://www.youtube.com/watch?v=stGq8lnEFlM

@Entity
@Table(name = "User")
public class User {

	public enum Role {
		USER, ADMIN
	}

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserId")
	private Integer id;
	
	@Column(name = "FirstName", nullable=false)
	private String firstName;

	@Column(name = "LastName", nullable=false)
	private String lastName;

	@Column(name = "Email", nullable=false, unique= true)
	@Email
	private String email;

	@Column(name="Active")
	private boolean active;

	@Column(name="Password", nullable=false)
	private String password;
	
	@Column(name="Type", nullable=false)
	private String type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role = Role.USER;
	
	//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "user")
	//private List<Shift> shifts;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="usersShifts")
	@JsonBackReference
	private Set<Shift> shifts = new HashSet<Shift>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private List<Request> requests;
	
	public User()
	{
		
	}
	
	public User(User user)
	{
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.type = user.getType();
	}
	
	public User(String firstName, String lastName, String email, String password, boolean active, Role role, String type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.role = role;
		this.type = type;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	/*public List<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}*/

	

	public List<Request> getRequests() {
		return requests;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Set<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(Set<Shift> shifts) {
		this.shifts = shifts;
	}
	
	
	public void addShift(Shift shift)
	{
		shifts.add(shift);
	}
	

}
