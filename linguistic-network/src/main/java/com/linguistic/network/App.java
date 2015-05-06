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
		
		Graphlogic graphLogic = new Graphlogic(jf, session, Constants.HABERTURK);
		graphLogic.createGraphByLinkType(Constants.LINK_SEQUENTIAL, 600);
		graphLogic.createGraphByLinkType(Constants.LINK_MESH, 600);
		
		System.out.println("NETWORKS CREATED");
		session.getTransaction().commit();
		session.close();
		jf.close();
	}
}
