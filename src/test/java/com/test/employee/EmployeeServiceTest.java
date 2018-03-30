package com.test.employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.employee.model.Employee;
import com.test.employee.repository.EmployeeRepository;
import com.test.employee.service.EmployeeService;;

/**
 * @author Anita Y	
 * Test class to test Employee service.
 * test cases written to test Get,Post,Put and Delete.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllEmployee() {
		List<Employee> employeeList = new ArrayList<>(Arrays.asList(new Employee(1001, "Jason", 2000),
				new Employee(1002, "Michael", 3000), new Employee(1003, "Susan", 4000)));
		when(employeeRepository.findAll()).thenReturn(employeeList);
		List<Employee> result = employeeService.getAllEmployees();
		assertEquals(3, result.size());
	}

	@Test
	public void testGetEmployeeById() {
		Employee employee = new Employee(2001, "Samuel", 5000);
		when(employeeRepository.findOne(2001L)).thenReturn(employee);
		Employee result = employeeService.getEmployeeByID(2001L);
		assertEquals(2001, result.getId());
		assertEquals("Samuel", result.getName());
		assertEquals(5000, result.getSalary());
	}

	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee(3001, "Stephanie", 6000);
		when(employeeRepository.save(employee)).thenReturn(employee);
		Employee result = employeeService.addEmployee(employee);
		assertEquals(3001, result.getId());
		assertEquals("Stephanie", result.getName());
		assertEquals(6000, result.getSalary());
	}

	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee(3001, "Barbara", 9000);
		when(employeeRepository.save(employee)).thenReturn(employee);
		Employee result = employeeService.updateEmployee(employee, employee.getId());
		assertEquals(3001, result.getId());
		assertEquals("Barbara", result.getName());
		assertEquals(9000, result.getSalary());
	}

	@Test
	public void testDeleteEmployee() {
		Employee employee = new Employee(2001, "Samuel", 5000);
		employeeService.deleteEmployee(employee.getId());
		verify(employeeRepository, times(1)).delete(employee.getId());

	}

}