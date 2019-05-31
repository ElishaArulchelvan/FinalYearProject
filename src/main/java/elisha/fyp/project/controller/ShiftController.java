package elisha.fyp.project.controller;


import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import elisha.fyp.project.entity.Request;
import elisha.fyp.project.entity.Shift;
import elisha.fyp.project.entity.Status;
import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.RequestService;
import elisha.fyp.project.service.ShiftService;
import elisha.fyp.project.service.UserService;


@Controller
public class ShiftController{
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Shift.class, new CustomDateEditor(sdf, true));
    }
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@RequestMapping(value = "/roster", method = RequestMethod.GET)
	public String getRosterPage(Model model) {

		return "roster";
	}
	
	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String getRequestPage(Model model) {

		List<User> users = userService.findAll();
		model.addAttribute("usersList", users);
		return "google";
	}
	
	@RequestMapping(value = "/viewRoster", method = RequestMethod.GET)
	public String getViewRoster() {

		return "viewRoster";
	}
	
	@RequestMapping(value = "/google/events/create", method = RequestMethod.POST)
	@JsonSerialize
	@Transactional
	@ResponseBody
	Shift createShift(@RequestBody ShiftParams params)
	{
		
		System.out.println("TEXT "+ params.text );
		User u = userService.findByFirstName(params.text);
		Set<User> userSets = new HashSet<>();
		userSets.add(u);
		
		List<Request> req = u.getRequests();
		
		if(req == null || req.isEmpty() || hasNoDaysOffOnShiftDate(req, params))
		{
			Shift shift = new Shift();
			shift.setStart(params.start);
			shift.setEnd(params.end);
			shift.setUsersShifts(userSets);
			shiftService.save(shift);
			System.out.println("Shift made");
			return shift;
		}else 
		{
			System.out.println("error");
		}
		return null;
		
		
		//System.out.println("******** GOT HERE");
		//System.out.println(params.start);
		//System.out.println(params.end);
		
		
		/*System.out.println("TEXT "+ params.text );
		User u = userService.findByFirstName(params.text);
		Set<User> userSets = new HashSet<>();
		userSets.add(u);
		
		List<Request> req = u.getRequests();
		
		
		/*if(req == null && req.isEmpty())
		{
			Shift shift = new Shift();
			shift.setStart(params.start);
			shift.setEnd(params.end);
			shift.setUsersShifts(userSets);
			shiftService.save(shift);
			
		}
		else 
		{
			for(Iterator<Request> it= req.iterator(); it.hasNext();)
			{
				Request empReq = it.next();
				System.out.println("Request dates " + empReq.getDate());
				
			
				/*if(empReq.getStatus() == Status.Accepted) 
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //for string
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //for localdate
				
					//Request start date in LocalDate
					Date d = empReq.getDate();
					String rDateStart = sdf.format(d);
					LocalDate reqStartDate = LocalDate.parse(rDateStart, formatter).minusDays(1);
					System.out.println("Req start date is " + reqStartDate);
			
					//Request end date in LocalDate
					int numDays = empReq.getNumDays();
					LocalDate endDateReq = reqStartDate.plusDays(numDays).plusDays(1);
					System.out.println("Request finish date is" + endDateReq);
			
					//Shift start date in LocalDate
					String shiftDate = (params.start).format(formatter);
					LocalDate formatDateTime = LocalDate.parse(shiftDate, formatter);
					System.out.println("Shift date in date format " + formatDateTime); 
			
					if(formatDateTime.isAfter(reqStartDate) && formatDateTime.isBefore(endDateReq))
					{
						System.out.println("The employee has requested that day off");
					} else {
						
						System.out.println("Goes here");
						Shift shift = new Shift();
						shift.setStart(params.start);
						shift.setEnd(params.end);
						shift.setUsersShifts(userSets);
						shiftService.save(shift);
						
					}
				
				}
			}
		return null; */
		
	}
	
	private boolean hasNoDaysOffOnShiftDate(List<Request> requests, ShiftParams params)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //for string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //for localdate
		
		//Shift start date in LocalDate
		String shiftDate = (params.start).format(formatter);
		LocalDate formatDateTime = LocalDate.parse(shiftDate, formatter);
		System.out.println("Shift date in date format " + formatDateTime); 
		
		Optional<Request> request = requests
				.stream()
				.filter(req -> req.getStatus() == Status.Accepted)
				.filter(req -> isDayOffOnShiftDate(req, formatDateTime))
				.findAny();
		return !request.isPresent();
		
	}
	
	private boolean isDayOffOnShiftDate(Request empReq, LocalDate formatDateTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //for string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //for localdate
	
		//Request start date in LocalDate
		Date d = empReq.getDate();
		String rDateStart = sdf.format(d);
		LocalDate reqStartDate = LocalDate.parse(rDateStart, formatter).minusDays(1);
		System.out.println("Req start date is " + reqStartDate);

		//Request end date in LocalDate
		int numDays = empReq.getNumDays();
		LocalDate endDateReq = reqStartDate.plusDays(numDays).plusDays(1);
		System.out.println("Request finish date is" + endDateReq);
		
		if (formatDateTime.isAfter(reqStartDate) && formatDateTime.isBefore(endDateReq)) //checks is the user has requested day off
        {
            System.out.println("The employee has requested that day off");
            return true;
        }else {
            return false;
        }
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteShift(@PathVariable int id, Model model)
	{
		Shift shift = shiftService.findOne(id);
		shiftService.deleteShift(shift);
		return "main";		
	}
	
	@RequestMapping(value = "/google/events/delete/{id}", method = RequestMethod.GET)
	@JsonSerialize
	@Transactional
	@ResponseBody
	Shift deleteShift(@PathVariable int id)
	{
		System.out.println("SHIFT ID " + id);
		Shift shift = shiftService.findOne(id);
		shiftService.deleteShift(shift);
		return shift;
	}
	
	
	//USE THIS FOR BACK END OF SHIFT VIEWING ON CALENDAR
	@RequestMapping(value = "/google/events")
	@ResponseBody
	//@JsonSerialize(using = LocalDateTimeSerializer.class)
	List <Shift> shifts (@RequestBody @RequestParam("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end)
	{
		
		List<Shift> shiftsToReturn = shiftService.findBetween(start, end);
		System.out.println(shiftsToReturn);
		return shiftsToReturn;
	}

	
	
	//USE THIS ONE FOR SHIFT FORM
	@RequestMapping(value = { "/roster" }, method = RequestMethod.POST)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public ModelAndView rosterCreate( @RequestParam("date") String date1, @RequestParam("start") String startTime1, @RequestParam("end") String endTime1, Principal principal, Model model)
	{
			
		try {
		
			//Use 2019-03-14 for date and 11:00 for time in form
			LocalDate newDate = LocalDate.parse(date1);
			
			LocalTime newStartTime = LocalTime.parse(startTime1);
			LocalDateTime startDateTime = LocalDateTime.of(newDate, newStartTime);
			
			LocalTime newEndTime = LocalTime.parse(endTime1);
			LocalDateTime endDateTime = LocalDateTime.of(newDate, newEndTime);
		
			Shift shift = new Shift();
			shift.setStart(startDateTime);
			shift.setEnd(endDateTime);
			//shift.setType(type1); 
		
			ModelAndView mv = new ModelAndView("success");
			mv.addObject("shift", shift);
			shiftService.save(shift);
		
			return mv;
		}
			catch(Exception e)
			{
				System.out.println("Error parsing date " + date1);
				System.out.println("Error parsing startTime " + startTime1);
				System.out.println("Error parsing endTime " + endTime1);
			}
		return null;
			
	
	}
	
	
	/*@RequestMapping(value="/shift/{id}", method = RequestMethod.GET)
	public String showUserShifts(@PathVariable Integer id, Model model)
	{
		
		User user = userService.findOne(id);
		
		
		Set<Shift> shifts = shiftService.findByUsersShifts(user);
		model.addAttribute("shiftList", shifts);
		return "shifts";
	}*/
	
	@RequestMapping(value="/showUserShifts")
	public String showUserShifts(@RequestParam("id") Integer id, Principal principal, Model model)
	{
		
		User user = userService.findOne(id);
		
		System.out.println("user id: " + user.getFirstName());
		Set<Shift> userShifts = shiftService.findByUsersShifts(user);
		
		LocalDateTime now = LocalDateTime.now().minusDays(1);
		System.out.println("The date today is: " + now);
		LocalDateTime then = now.plusDays(7);
		
		Set<Shift> shifts = new HashSet<>();
		
		for(Iterator<Shift> it= userShifts.iterator(); it.hasNext(); ) {
			Shift s = it.next();
			
			if(s.getStart().isBefore(then) && s.getStart().isAfter(now))
			{
				System.out.println("THIS IS THE START " +s.getStart());
				
				shifts.add(s);
				
			}
			
		}
		model.addAttribute("shiftList", shifts);
		//model.addAttribute("shiftList", shifts);
		
		return "shifts";
	}
	
	
	
	
	
	@RequestMapping(value = "/assignShift", method = RequestMethod.GET)
	public String getAssignShift(Model model) {

		List<User> users = userService.findAll();
		model.addAttribute("usersList", users);
		List<Shift> shifts = shiftService.findAll();
		model.addAttribute("shiftsList", shifts);
		return "assignShift";
	}
	
	@RequestMapping(value = { "/assignShift" }, method = RequestMethod.POST)
	public ModelAndView assignShift(@RequestParam("Shift") String shift, @RequestParam("User") String user, Principal principal, Model model)
	{
		
		int shiftID = Integer.parseInt(shift);
		Shift shift2 = shiftService.findOne(shiftID);
		
	
		int userID = Integer.parseInt(user);
		User user2 = userService.findOne(userID);
	
		//List<Request> reqs = user2.getRequests();
		//List<Request> reqs = requestService.findByUser(user2);
		
		
		//if(shift2.getDate()==  )

		
		shift2.addUser(user2); 
		
		ModelAndView mv = new ModelAndView("success");
		mv.addObject("shift", shift);
		shiftService.save(shift2);
		
		
		return  mv;
	
	}
	
	@RequestMapping(value ="/myShifts", method = RequestMethod.GET)
	public String myShifts(Principal principal, Model model)
	{
		String email = principal.getName();
		User user = userService.findByEmail(email);
		System.out.println("USer is :" + user.getFirstName());
		
		Set<Shift> userShifts = shiftService.findByUsersShifts(user);
		
		LocalDateTime now = LocalDateTime.now().minusDays(1);
		System.out.println("The date today is: " + now);
		LocalDateTime then = now.plusDays(7);
		
		Set<Shift> shifts = new HashSet<>();
		
		for(Iterator<Shift> it= userShifts.iterator(); it.hasNext(); ) {
			Shift s = it.next();
			
			if(s.getStart().isBefore(then) && s.getStart().isAfter(now))
			{
				System.out.println("THIS IS THE START " +s.getStart());
				
				shifts.add(s);
			}
			
		}
		model.addAttribute("shiftList", shifts);
		
		return "shifts";
	}
	
	@RequestMapping(value ="/holidays", method = RequestMethod.GET)
	public String getHolidays(Model model, Principal principal)
	{
		String email = principal.getName();
		User user = userService.findByEmail(email);
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
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				cal.set(Calendar.DATE, 1);
				Date firstDayOfPrevMonth = cal.getTime();
				System.out.println("Start date of prev month " + firstDayOfPrevMonth);
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date lastDayOfPrevMonth = cal.getTime();
				System.out.println("Start date of prev month " + lastDayOfPrevMonth);
				
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
			
			
			
		model.addAttribute("totalTime", totalTime);
		model.addAttribute("totalTimeMonth", totalTimeMonth);
		model.addAttribute("averageWeek", averageWeekHours);
		model.addAttribute("holidayHours", holidayHours);
		return "holidays";
	}
	
	
	
	public static class ShiftParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public int id;
        //public int type;
    }
	

	public static class ShiftDeleteParams {
        public int id;
        public LocalDateTime start;
        public LocalDateTime end;
    }
	
	
	
}


