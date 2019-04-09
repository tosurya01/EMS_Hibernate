package com.evry.ems.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.evry.ems.model.Department;
import com.evry.ems.model.Employee;
import com.evry.ems.util.HibernateUtil;

/**
 * 
 * @author venkata.kuppili
 *
 */
public class DepartmentDao {
	/**
	 */
	public void getDepartmentMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("Please select the following option ");
			System.out.println("1 -> Add Department");
			System.out.println("2 -> Update Department");
			System.out.println("3 -> All Departments");
			System.out.println("4 -> Get Department");
			System.out.println("5 -> Delete Department");
			System.out.println("6 -> Logout");

			int choice = scanner.nextInt();
			long id;
			switch (choice) {
			case 1:
				this.addDepartment(scanner);
				break;

			case 2:
				this.updateDepartment(scanner);
				break;
			case 3:
				this.getAllDepartments();
				break;
			case 4:
				System.out.println("Please enter department id");
				id = scanner.nextLong();
				this.getDepartment(id);
				break;
			case 5:
				System.out.println("Please enter deleted department id");
				id = scanner.nextLong();
				this.deleteDepartment(id);
				break;
			case 6:
				flag = false;
				break;
			default:
				System.out.println("Please choose proper option.");
				break;
			}
		}
	}

	/**
	 * 
	 * @param scanner
	 */
	public void addDepartment(Scanner scanner) {
		Department details = new Department();
		System.out.println("Please enter Department name:");
		String departmentName = scanner.next();
		details.setDepartmentName(departmentName);
		System.out.println("Please enter Country:");
		String country = scanner.next();
		details.setCountry(country);
		boolean temp = true;
		//while (temp) {
			System.out.println("Please select the following option ");
			System.out.println("1 -> Add Employee");
			System.out.println("2 -> Exit");
			int select = scanner.nextInt();
			List<Employee> employeeList = new ArrayList<Employee>();
			switch (select) {
			case 1:
				Employee employeeDetails = new Employee();
				System.out.println("Please enter Employee first name:");
				String firstName = scanner.next();
				employeeDetails.setFirstName(firstName);
				System.out.println("Please enter Employee last name:");
				String lastName = scanner.next();
				employeeDetails.setLastName(lastName);
				System.out.println("Please enter Employee address:");
				String address = scanner.next();
				employeeDetails.setAddress(address);
				employeeDetails.setDepartment(details);
				employeeList.add(employeeDetails);
				details.setEmployees(employeeList);
				Session session = HibernateUtil.getSession();
				session.beginTransaction();
				session.persist(details);
				session.getTransaction().commit();
				session.close();
				break;
			case 2:
				temp = false;
				break;
			default:
				System.out.println("Please choose proper option");
				break;
			}
		//}
	}

	/**
	 * 
	 * @param scanner
	 */
	public void updateDepartment(Scanner scanner) {
		Session session = HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		Department department = new Department();
		System.out.println("Please Enter DepartmentId:");
		department.setDepartmentId(scanner.nextLong());
		System.out.println("Please Enter Department name:");
		department.setDepartmentName(scanner.next());
		System.out.println("Please Enter country name:");
		department.setCountry(scanner.next());
		try {
			if (!tr.isActive())
				tr.begin();
			session.merge(department);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}
		session.beginTransaction().commit();
		session.close();

	}

	/**
	 * 
	 */
	public void getAllDepartments() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Department");
		List<Department> result = query.list();
		System.out.println("Id\tName\tCountry");
		System.out.println("----------------------------");
		for (Department dept : result) {
			System.out.print(dept.getDepartmentId() + "\t");
			System.out.print(dept.getDepartmentName() + "\t");
			System.out.print(dept.getCountry() + "\t");
			System.out.println();
		}
		session.close();
	}

	/**
	 * 
	 * @param id
	 */
	public void getDepartment(final long id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Department where departmentId= :deptId");
		query.setParameter("deptId", id);
		List<Department> result = query.list();
		System.out.println("Id\tName\tCountry");
		System.out.println("----------------------------");
		for (Department dept : result) {
			System.out.print(dept.getDepartmentId() + "\t");
			System.out.print(dept.getDepartmentName() + "\t");
			System.out.print(dept.getCountry() + "\t");
			System.out.println();
		}
		session.close();
	}

	/**
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteDepartment(final long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {

			Department dept = session.get(Department.class, id);
			if (dept != null) {
				session.delete(dept);
				System.out.println("One department is deleted");
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
