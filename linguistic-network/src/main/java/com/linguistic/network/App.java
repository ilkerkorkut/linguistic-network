package com.linguistic.network;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.linguistic.network.entity.Edges;
import com.linguistic.network.entity.Nodes;

public class App {
	private static String CLEAR_SENTENCE = "[^a-zA-ZİıŞşÜüĞğÖöÇç.!,'?^%()0-9\\s]";
	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.configure();
		
		ServiceRegistry serviceRegistry = serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		JedisFactory jf = new JedisFactory();
		
		Graphlogic graphlogic = new Graphlogic();
		graphlogic.createSequentialLinks(jf,session,Constants.HABERTURK);
		graphlogic.createSequentialLinks(jf,session,Constants.TRTHABER);
		graphlogic.createSequentialLinks(jf,session,Constants.HURRIYET);
		graphlogic.createSequentialLinks(jf,session,Constants.ACUNNCOM);
		
		System.out.println("NETWORKS CREATED");
		session.getTransaction().commit();
		session.close();
	}
}
