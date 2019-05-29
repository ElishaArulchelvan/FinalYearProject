package elisha.fyp.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Announcement")
public class Announcement {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "AnnouncementId")
	int id;
	
	@Column(name = "datePosted", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date datePosted;
	
	@Column(name="message")
	private String message;
	
	public Announcement()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
