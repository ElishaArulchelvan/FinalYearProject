package elisha.fyp.project.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import elisha.fyp.project.entity.Announcement;
import elisha.fyp.project.entity.Request;
import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.AnnouncementService;
import elisha.fyp.project.service.UserService;

@Controller
@Transactional
@EnableWebMvc
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@RequestMapping(value = {"/employeeList"}, method = RequestMethod.GET)
	public String showUsers(Model model)
	{
		List<User> users = userService.findAll();
		model.addAttribute("usersList", users);
		return "employeeList";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterPage() {

		return "register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String registerConfirm(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/main";	

	}

	@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable Integer id) {

		userService.delete(id);

		return "redirect:/users";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth !=null)
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value = "/editInfo", method = RequestMethod.GET)
	public String editInfo(Model model, Principal principal) {
		
		System.out.println("HERE!!!!");
		String email1 = principal.getName();
		User user = userService.findByEmail(email1);
		
		model.addAttribute("user", user);
		
		return "editInfo";
	}
	
	
	@RequestMapping(value = { "/editInfo" }, method = RequestMethod.POST)
	public ModelAndView editDetails(Model model, Principal principal, @RequestParam("email") String email, @RequestParam("password") String password)
	{
		String email1 = principal.getName();
		User user = userService.findByEmail(email1);
		
		
		user.setEmail(email);
		user.setPassword(password);
		
		
		ModelAndView mv = new ModelAndView("success");
		mv.addObject("user", user);
		
		userService.updateUser(user);
		return mv;
	}
	
	
	private String getPrincipal()
	{
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails)
		{
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	@RequestMapping(value = { "/createAnnouncement" }, method = RequestMethod.POST)
	public String createAnnouncement(Model model, @RequestParam("message") String message)
	{
		List<Announcement> announcements = announcementService.findAll();
		model.addAttribute("announcements", announcements);
		
		
		Calendar calc = Calendar.getInstance();
		Date now = calc.getTime();
		System.out.println("Date now is: " + now);
		System.out.println("Message is " + message);
		
		Announcement announcement = new Announcement();
		announcement.setMessage(message);
		announcement.setDatePosted(now);
		announcementService.save(announcement);
		
		return "main";
		
	}
	
	
	
	

}