package elisha.fyp.project.config;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import elisha.fyp.project.converter.StringToUserConverter;

@Configuration
@ComponentScan("elisha.fyp.project")
@EnableWebMvc
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class AppConfig extends WebMvcConfigurerAdapter{
	
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String dateFormat = "yyyy-MM-dd";
	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Autowired
    StringToUserConverter stringToUserConverter; 
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
		
        registry.addConverter(stringToUserConverter);
    }
	
	
	/*@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() 
	{
		Jackson2ObjectMapperBuilder con = new Jackson2ObjectMapperBuilder();
        return new Jackson2ObjectMapperBuilderCustomizer() 
        {
			
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				// TODO Auto-generated method stub
				jacksonObjectMapperBuilder.simpleDateFormat(dateTimeFormat);
				jacksonObjectMapperBuilder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
				jacksonObjectMapperBuilder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
				
			}
        };
	}*/
}
	
	

