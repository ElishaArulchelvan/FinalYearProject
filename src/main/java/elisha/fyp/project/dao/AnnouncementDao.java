package elisha.fyp.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elisha.fyp.project.entity.Announcement;

@Repository
public interface AnnouncementDao extends JpaRepository<Announcement, Integer>{

	List<Announcement> findAll();
	Announcement findById(int id);
	

}
