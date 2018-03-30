package com.test.employee.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private long id;
	private String name;
	private long salary;

	public Employee() {
		super();
	}

	public Employee(long id, String ename, int sal) {
		super();
		this.id = id;
		this.name = ename;
		this.salary = sal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

}
