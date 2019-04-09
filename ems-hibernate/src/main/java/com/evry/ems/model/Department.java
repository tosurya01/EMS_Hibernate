package com.evry.ems.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "DEPARTMENT")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_ID")
	private long departmentId;
	@Column(name = "NAME")
	private String departmentName;
	private String country;
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Employee> employees;

	public Department() {
		super();
	}

	public Department(final String departmentName, final String country, final List<Employee> employees) {
		super();
		this.departmentName = departmentName;
		this.country = country;
		this.employees = employees;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(final long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(final String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(final List<Employee> employees) {
		this.employees = employees;
	}

}
