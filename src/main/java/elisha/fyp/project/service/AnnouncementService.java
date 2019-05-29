package elisha.fyp.project.service;

import java.util.List;

import elisha.fyp.project.entity.Announcement;

public interface AnnouncementService {

	void save(Announcement announcement);
	void delete(int id);
	List<Announcement> findAll();
	Announcement findOne(int id);
	
	void deleteAnnouncement(Announcement announcement);
}
