package elisha.fyp.project.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import elisha.fyp.project.entity.User;
import elisha.fyp.project.service.UserService;

@Component
public class StringToUserConverter implements Converter<String, User> {
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(String text) {
		
		List<User> users = userService.findAll();
		for(User u: users)
		{
			if(u.getId().toString().equalsIgnoreCase(text))
			{
				return u;
			}
		}
		return null;
		
	}
	

}
