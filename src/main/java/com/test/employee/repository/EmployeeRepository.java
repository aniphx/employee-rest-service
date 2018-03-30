package com.test.employee.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.employee.model.Employee;

@Repository
public interface EmployeeRepository  extends CrudRepository<Employee,Long>{
	

}
