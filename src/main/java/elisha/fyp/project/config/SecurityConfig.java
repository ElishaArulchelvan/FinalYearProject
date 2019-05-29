package elisha.fyp.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import elisha.fyp.project.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userService;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()        
		.antMatchers("/login").permitAll()
		.antMatchers("/register").hasRole("ADMIN")
		.antMatchers("/roster").hasRole("ADMIN")
		.antMatchers("/users/**", "/create-user").hasRole("ADMIN")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/viewRequests/**").hasRole("ADMIN")
		.antMatchers("/employeeList").hasRole("ADMIN")
		.antMatchers("/shifts/**").hasRole("ADMIN")
		.antMatchers("/assignShift").hasRole("ADMIN")
		.antMatchers("/google").hasRole("ADMIN")
		.antMatchers("/request").permitAll()
		.antMatchers("/request/**").permitAll()
		.antMatchers("/api/**").permitAll()
		.antMatchers("resources/**").permitAll()
		.antMatchers("css/**").permitAll()
		.antMatchers("/**").authenticated()
		.and()
		.formLogin()              
		.usernameParameter("email")
		.passwordParameter("password")
		.loginPage("/login")
		.loginProcessingUrl("/login")       
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login?logout")  
		.and()
		.csrf().disable();             
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/css/**");
	}
	

}
