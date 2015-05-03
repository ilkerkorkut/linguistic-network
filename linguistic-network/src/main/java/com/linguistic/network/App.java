package com.linguistic.network;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.configure();
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		JedisFactory jf = new JedisFactory();
		
		Graphlogic graphlogic = new Graphlogic();
//		graphlogic.createSequentialLinks(jf,session,Constants.HABERTURK);
//		graphlogic.createSequentialLinks(jf,session,Constants.TRTHABER);
//		graphlogic.createSequentialLinks(jf,session,Constants.HURRIYET);
//		graphlogic.createSequentialLinks(jf,session,Constants.ACUNNCOM);
		
		graphlogic.createMeshLinks(jf, session, Constants.HABERTURK);
		
		System.out.println("NETWORKS CREATED");
		session.getTransaction().commit();
		session.close();
	}
}
