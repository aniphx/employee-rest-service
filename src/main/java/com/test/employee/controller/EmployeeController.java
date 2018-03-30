package com.test.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.employee.model.Employee;
import com.test.employee.service.EmployeeService;

/**
 * @author Anita Y
 *  Controller class ,redirected as per the rest URL request.
 * methods  implemented  to get all employees, 
 * create ,update and delete employee.
 */
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET, value = "/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();

	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/{id}")
	public Employee getEmployeeByID(@PathVariable Long id) {
		return employeeService.getEmployeeByID(id);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		return employeeService.updateEmployee(employee, id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}

}
