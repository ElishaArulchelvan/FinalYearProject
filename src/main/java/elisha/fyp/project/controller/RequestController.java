package elisha.fyp.project.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



import elisha.fyp.project.entity.Request;
import elisha.fyp.project.entity.Status;
import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.RequestService;
import elisha.fyp.project.service.UserService;


@Controller
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private UserService userService;
	
	/*@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {

		Object obj = dataBinder.getTarget();
		if (obj == null) {
			return;
		}
		System.out.println("Target=" + obj);

	}*/
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Request.class, new CustomDateEditor(sdf, true));
    }
	
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	{
		binder.registerCustomEditor(Status.class, new PropertyEditorSupport()
		{
			
			@Override
			public void setAsText(String value) throws IllegalArgumentException {
			 
			setValue(Status.valueOf(value));
			}
			 
			@Override
			public String getAsText() {
			if(getValue() == null)
			return "";
			 
			return ((Status) getValue()).name();
			}
			
		});
		
		
	}
	
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String getRequestPage() {

		return "request";
	}
	
	/*@RequestMapping(value = { "/request" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String requestConfirm(@ModelAttribute Request request) {
		requestService.save(request);
		return "redirect:/main";	

	}*/
	
	
	@RequestMapping(value = {"/request"}, method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView requestConfirm( @RequestParam("date") String date1, @RequestParam("numDays") int numDays, @RequestParam("reason") String reason1, Principal principal)
	{
		//User user = new User();
		String email = principal.getName();
		User user = userService.findByEmail(email);
		System.out.println("Date: " + date1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			Date newDate = sdf.parse(date1);
			System.out.println("New date: " + newDate);
		
			List<Request> requests = requestService.findAll();
			
			ArrayList<Request> results = new ArrayList<Request>();
			
			for(Iterator<Request> it = requests.iterator(); it.hasNext(); )
			{
				Request req = it.next();
				
				if(req.getDate().equals(newDate) && req.getStatus().equals(Status.Accepted))
				{
					results.add(req);
					
					
				}
				Calendar calc = Calendar.getInstance();
				calc.setTime(req.getDate());
				calc.add(Calendar.DATE, req.getNumDays());
				//System.out.println("START " + req.getDate() + " END" + calc.getTime());
				Date endDate = calc.getTime();
				
				/*if(newDate.before(endDate) || newDate.after(req.getDate()) && req.getStatus().equals(Status.Accepted) )
				{
					results.add(req);
				}*/
				
				
			}
			
			System.out.println("The number of people " + results.size()); 
			
			if(results.size() >=3)
			{
				//String error= null;
				System.out.println("Too many people asked for that day off");
				ModelAndView newMv = new ModelAndView("unsuccessful");
				return newMv;
				
				
			}
			else
			{
				Request request = new Request();
				request.setDate(newDate);
				request.setNumDays(numDays);
				request.setReason(reason1);
				request.setUser(user);
				request.setStatus(Status.Pending);
				System.out.println("Request made");
			
				ModelAndView mv = new ModelAndView("successRequest");
				mv.addObject("request", request);
				//mv.addObject("user", user);
			
				requestService.save(request);
				//System.out.println("Request saved in date format: " + newDate);
				return mv;
			}
			
			}
			catch(Exception e) {
				System.out.println("Error parsing date " + date1);
			}
	
		return null;
	}
	
	
	
	/*@RequestMapping(value = {"/request"}, method = RequestMethod.POST)
	public ModelAndView requestConfirm( @RequestParam("date") String date1, @RequestParam("numDays") int numDays, @RequestParam("reason") String reason1, Principal principal)
	{
		//User user = new User();
		String email = principal.getName();
		User user = userService.findByEmail(email);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try
		{
			Date newDate = sdf.parse(date1);
			System.out.println("Date in date format is:" + newDate);
			
			
			Request request = new Request();
			request.setDate(newDate);
			request.setNumDays(numDays);
			request.setReason(reason1);
			request.setUser(user);
			request.setStatus(Status.Pending);
			
			ModelAndView mv = new ModelAndView("successRequest");
			mv.addObject("request", request);
			//mv.addObject("user", user);
			
			requestService.save(request);
			//System.out.println("Request saved in date format: " + newDate);
			return mv;
		}
		catch(Exception e) {
			System.out.println("Error parsing date " + date1);
		}
		return null;
		
			
		
	
		
		/*Request request = new Request();
		request.setDate(newDate);
		request.setNumDays(numDays);
		request.setReason(reason1);
		request.setUser(user);
		request.setStatus(Status.Pending);
		
		ModelAndView mv = new ModelAndView("successRequest");
		mv.addObject("request", request);
		//mv.addObject("user", user);
		
		requestService.save(request);
		
		
		//return  mv;
	}*/
	
	
	
	@RequestMapping(value="/viewRequests", method = RequestMethod.GET)
	public String showRequests(Model model)
	{
		//List<Request> requests = requestService.findAll();
		//model.addAttribute("requestList", requests);
		//return "viewRequests";
		
		List<Request> requests = requestService.findByStatus(Status.Pending);
		model.addAttribute("requestList", requests);
		return "viewRequests";
	}
	
	/*@RequestMapping(value="/searchByEmployee")
	public String showRequestByEmployee(@PathVariable("id") Integer id, Principal principal, Model model)
	{
		String email = principal.getName();
		User user = userService.findByEmail(email);
		id = user.getId();
		
		List<Request> requests = requestService.findByUserId(id);
		model.addAttribute("requestList", requests);
		return "request";
	}*/
	
	/*@RequestMapping(value = "/request/edit/{id}", method = RequestMethod.GET)
	public String getEditRequestForm(Model model, @PathVariable Long id) {

		Request request = requestService.findByUserId(id);

		model.addAttribute("requestForm", request);

		return "book"; 
	}*/
	
	
	
	
	@RequestMapping(value = {"/decline/{id}"}, method = RequestMethod.GET)
	public String declineRequest(@PathVariable Long id, Model model)
	{
		
		Request request = requestService.findOne(id);
		request.setStatus(Status.Declined);
	
		model.addAttribute("request", request);
		//requestService.updateRequest(request);
		requestService.save(request);
		
		return "viewRequests";
	}
	
	@RequestMapping(value = {"/accept/{id}"}, method = RequestMethod.GET)
	public String acceptRequest(@PathVariable Long id, Model model)
	{
		Request request = requestService.findOne(id);
		request.setStatus(Status.Accepted);
		
		model.addAttribute("request", request);
		requestService.save(request);
		
		return "viewRequests";
	}
	
	@RequestMapping(value="/myRequests", method= RequestMethod.GET)
	public String myRequests(Principal principal, Model model)
	{
		String email = principal.getName();
		User user = userService.findByEmail(email);
		
		List<Request> requests = requestService.findByUser(user);
	
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		System.out.println(dateFormat.format(now));
		
		List<Request> upcomingRequests = new ArrayList<>();
		
		for(Iterator<Request> it=requests.iterator(); it.hasNext();)
		{
			Request r = it.next();
			
			if(r.getDate().after(now))
			{
				upcomingRequests.add(r);
			}
			
		}
		model.addAttribute("requests", upcomingRequests);
		return "myRequests";
		
	}
	
	
	
	
	
	

}
