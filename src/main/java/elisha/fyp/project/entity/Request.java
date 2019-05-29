package elisha.fyp.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "Request")
public class Request {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RequestId")
	long id;
	
	@Column(name = "date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "numDays", nullable=false)
	private int numDays;
	
	@Column(name = "reason", nullable=false)
	private String reason;
	
	/*@ManyToOne
	private User user;*/
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	private User user;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Status")
	private Status status;
	
	public Request()
	{
		
	}
	
	public Request(User user)
	{
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	

}
