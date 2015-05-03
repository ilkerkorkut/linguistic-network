package com.linguistic.network;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.linguistic.network.entity.Edges;
import com.linguistic.network.entity.Nodes;

public class Graphlogic {
	
	public void createSequentialLinks(JedisFactory jf, Session session, String redisSetKey){
		int k = 0;
		for(String s: jf.getRedisSet(redisSetKey)){
			
			if(k<900000){ // TODO : REMOVE Test purpose only, records limit
				s = s.replaceAll(Constants.CLEAR_SENTENCE, "");
				
				// Comment length should be greater than 10 character.
				if(s.length() > 10){
					String[] sentence = s.split(" ");
					List<Nodes> nodesList = new ArrayList<Nodes>();
					for(int i=0;i < sentence.length; i++){
						Nodes node = new Nodes();
						node.setLabel(sentence[i]);
						node.setNodes(sentence[i]);
						nodesList.add(node);
						session.save(node);
					}
					int index = 0;
					for(Nodes nodes : nodesList){
						Edges edges = new Edges();
						edges.setLabel("");
						edges.setType("Undirected");
						edges.setSource(nodes.getId());
						edges.setWeight(1);
						if(index < nodesList.size()-1){
							edges.setTarget(nodesList.get(index+1).getId());
							session.save(edges);
						}
						index++;
					}
				}
				
			}
			k++;
			
		}
	}
	
	public void createMeshLinks(JedisFactory jf, Session session, String redisSetKey){
		int k = 0;
		for(String s: jf.getRedisSet(redisSetKey)){
			s = s.replaceAll(Constants.CLEAR_SENTENCE, "");
			if(k<600){
				if(s.length() > 10){
					
					String[] sentence = s.split(" ");
					List<Nodes> nodesList = new ArrayList<Nodes>();
					for(int i=0;i < sentence.length; i++){
						Nodes node = new Nodes();
						node.setLabel(sentence[i]);
						node.setNodes(sentence[i]);
						nodesList.add(node);
						session.save(node);
					}
									
					int index = 0;
					for(Nodes nodes : nodesList){
						int subIndex = 0;
						for(Nodes ns : nodesList){
							int lastIndex = nodesList.size()-1;
							if(subIndex != index && index != lastIndex){
								Edges edges = new Edges();
								edges.setLabel("");
								edges.setType("Undirected");
								edges.setSource(nodes.getId());
								edges.setWeight(1);
								edges.setTarget(ns.getId());
								session.save(edges);
							}
							subIndex++;
						}
						index++;
					}
				}
			}
			k++;
			
		}
	}
	
}
