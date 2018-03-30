package com.test.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.employee.model.Employee;
import com.test.employee.repository.EmployeeRepository;

/**
 * @author Anita Y
 * Employee service class implements the functionality
 * to add,update,delete and list all employees.
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		List<Employee> employeesList = new ArrayList<>();
		employeeRepository.findAll().forEach(employeesList::add);
		return employeesList;
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee employee, Long id) {
		return employeeRepository.save(employee);

	}

	public void deleteEmployee(Long id) {
		employeeRepository.delete(id);

	}

	public Employee getEmployeeByID(Long id) {
		return employeeRepository.findOne(id);
	}

}
