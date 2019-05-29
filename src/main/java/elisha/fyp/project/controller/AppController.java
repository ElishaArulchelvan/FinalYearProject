package elisha.fyp.project.controller;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import elisha.fyp.project.entity.Announcement;
import elisha.fyp.project.entity.Shift;
import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.AnnouncementService;
import elisha.fyp.project.service.ShiftService;
import elisha.fyp.project.service.UserService;

@Controller
public class AppController {
	
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ShiftService shiftService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainPage(Model model, Principal principal) {

		List<Announcement> announcements = announcementService.findAll();
		model.addAttribute("announcements", announcements);
		
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		
		System.out.println("User is " + user.getFirstName());
		
		Set<Shift> shifts = shiftService.findByUsersShifts(user);
		
		long totalTime = 0;
		long totalTimeMonth = 0;
		long averageWeekHours = 0;
		double holidayHours = 0;
		
			for(Shift shift : shifts)
			{
				LocalTime startTime = shift.getStart().toLocalTime();
				LocalTime endTime = shift.getEnd().toLocalTime();
				
				Set<Shift> monthlyShifts = new HashSet<>();

				
				Long duration = Duration.between(startTime, endTime).toHours();
				//System.out.println("The time bewtween shifts is " + duration);
				
				totalTime += duration;
				
				
				LocalDate now = LocalDate.now();
				YearMonth month = YearMonth.from(now);
				LocalDate start = month.atDay(1);
				LocalDate end = month.atEndOfMonth();
				
				if(shift.getStart().toLocalDate().isBefore(end) && shift.getStart().toLocalDate().isAfter(start))
				{
					Long time = Duration.between(shift.getStart().toLocalTime(), shift.getEnd().toLocalTime()).toHours();
					//System.out.println("The time bewtween shifts is this month is " + time);
					
					totalTimeMonth += time;
					
					averageWeekHours = totalTimeMonth/4;
					
				}
				
			}
			System.out.println("The total hours youve worked this year is " + totalTime);
			System.out.println("The hours worked this month " + totalTimeMonth);
			System.out.println("The average hours a week they work is " + averageWeekHours);
			
			holidayHours = (totalTime * 0.08); //change this to get the exact weeks from start of year to current date
			
			model.addAttribute("totalTime", totalTime);
			model.addAttribute("totalTimeMonth", totalTimeMonth);
			model.addAttribute("averageWeek", averageWeekHours);
			model.addAttribute("holidayHours", holidayHours);
			model.addAttribute("");
		
		return "main";
	}
	
	@RequestMapping(value = {"/deleteAnnouncement/{id}"}, method = RequestMethod.GET)
	public String deleteAnnouncement(@PathVariable int id, Model model)
	{
		System.out.println("HERE!!!!");
		Announcement announcement = announcementService.findOne(id);
		announcementService.deleteAnnouncement(announcement);
		

		return "success";
		
		
	}
}
/*<div class="login-container">
<c:if test="${param.logout != null}">
<div class="alert alert-success fade in">
	<a class="close" data-dismiss="alert" href="#">&times;</a>
	<p>You've logged out</p>
</div>
</c:if>

<c:if test="${param.register != null}">
<div class="alert alert-info fade in">
	<a class="close" data-dismiss="alert" href="#">&times;</a>
	<p>Register successful. You can log in</p>
</div>
</c:if>

<c:if test="${param.error != null}">
<div class="alert alert-danger fade in">
	<a class="close" data-dismiss="alert" href="#">&times;</a>
	<p>Username or password is incorrect</p>
</div>
</c:if>
<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet"></link>
 *
 */