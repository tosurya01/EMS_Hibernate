package com.evry.ems.dao;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class DepartmentDaoTest {
	DepartmentDao department;
	Scanner scanner;
	@Before
	public void setUp() {
		department = new DepartmentDao();
		scanner = new Scanner(System.in);
	}

	@Test
	public void testGetDepartmentMenu() {
		department.getDepartmentMenu();
	}

	@Test
	public void testAddDepartment() {
		department.addDepartment(scanner);
	}

	@Test
	public void testUpdateDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllDepartments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDepartment() {
		fail("Not yet implemented");
	}

}
