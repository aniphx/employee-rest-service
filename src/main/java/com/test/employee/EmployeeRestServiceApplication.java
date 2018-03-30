package com.test.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.test.employee.model.Employee;
import com.test.employee.repository.EmployeeRepository;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class EmployeeRestServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRestServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRestServiceApplication.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner setup(EmployeeRepository employeeRepository) {
		return (args) -> {
			employeeRepository.save(new Employee(1001, "Jason", 2000));
			employeeRepository.save(new Employee(1002, "Susan", 3000));
			employeeRepository.save(new Employee(1003, "Mike", 1500));
			employeeRepository.save(new Employee(1004, "Natalie", 4000));
			logger.info("The sample data has been generated");
		};
	}
}
