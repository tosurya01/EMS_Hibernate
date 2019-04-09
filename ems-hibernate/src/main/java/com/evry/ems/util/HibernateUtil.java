package com.evry.ems.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * 
 * @author venkata.kuppili
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

	static {
		try {
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
					.build();
			Metadata meta = new MetadataSources(registry).getMetadataBuilder().build();
			sessionFactory = meta.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Exception while building session factory");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {

		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (Exception e) {
			System.err.println("Exception while getting session");
			e.printStackTrace();
		}
		if (session == null) {
			System.err.println("session is discovered null");
		}

		return session;
	}
}
