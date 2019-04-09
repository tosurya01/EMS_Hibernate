package com.evry.ems;

import java.util.Scanner;

import com.evry.ems.dao.DepartmentDao;

/**
 * 
 * @author venkata.kuppili
 *
 */
public class Management {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("Please enter the following option ");
			System.out.println("1 -> Department management");
			System.out.println("2 -> Exit");

			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				DepartmentDao departmentDao = new DepartmentDao();
				departmentDao.getDepartmentMenu();
				break;
			case 2:
				flag = false;
				break;
			default:
				System.out.println("Please choose proper option.");
				break;
			}
		}
	}
}
