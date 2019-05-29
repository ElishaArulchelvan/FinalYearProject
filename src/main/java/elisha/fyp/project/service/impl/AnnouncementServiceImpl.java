package elisha.fyp.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elisha.fyp.project.dao.AnnouncementDao;
import elisha.fyp.project.dao.ShiftDao;
import elisha.fyp.project.entity.Announcement;
import elisha.fyp.project.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{

	@Autowired
	private AnnouncementDao announcementDao;
	
	@Override
	public void save(Announcement announcement) {
		
		announcementDao.save(announcement);
		
	}

	@Override
	public void delete(int id) {
		
		announcementDao.delete(id);
	}

	@Override
	public List<Announcement> findAll() {
		
		return announcementDao.findAll();
	}

	@Override
	public Announcement findOne(int id) {
		
		return announcementDao.findById(id);
	}

	@Override
	public void deleteAnnouncement(Announcement announcement) {
		
		announcementDao.delete(announcement);
		
	}

}
