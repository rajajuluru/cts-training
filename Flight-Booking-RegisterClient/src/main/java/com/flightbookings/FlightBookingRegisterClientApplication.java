package com.flightbookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flightbookings.entities.UserCandidate;
import com.flightbookings.repositories.UserCandidateRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@Configuration
public class FlightBookingRegisterClientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingRegisterClientApplication.class, args);
	}
@Bean
public Docket changeView()
{
	
	return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any()).build();
	//return new Docket(DocumentationType.SWAGGER_2).select().apis(selector);
}

private final String AdminUserName = "admin@gmail.com";
private final String AdminPassword = "admin";
private final String AdminRoleId = "A";
@Autowired
UserCandidateRepository CandidateRepository;


@Override
public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
	boolean existsById = CandidateRepository.existsById(AdminUserName);
	UserCandidate userCandidate=new UserCandidate();
	if(existsById)
	{
		

	}else
	{
		userCandidate.setAddress("admin");
		userCandidate.setEmailid(AdminUserName);
		userCandidate.setFirstName("Admin");
		userCandidate.setLastName("admin");
		userCandidate.setMobilenumber("9999999999");
		userCandidate.setPassword(AdminPassword);
		userCandidate.setRoletype(AdminRoleId);
		CandidateRepository.save(userCandidate);
		
	}
	
}

/*@Bean 
public RestTemplate getRestTemplate() {
    return new RestTemplate();
}*/


/*@Bean
public FilterRegistrationBean <CustomeFilterConfig > filterRegistrationBean() {
 FilterRegistrationBean < CustomeFilterConfig > registrationBean = new FilterRegistrationBean();
 CustomeFilterConfig customURLFilter = new CustomeFilterConfig();

 registrationBean.setFilter(customURLFilter);
 registrationBean.addUrlPatterns("*");
 registrationBean.setOrder(1); //set precedence
 return registrationBean;
}*/
}
