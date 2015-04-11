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
			
//			if(k<2){ // TODO : REMOVE Test purpose only, records limit
				System.out.println("================================");
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
						System.out.println("word: " +node.toString());
					}
					int index = 0;
					for(Nodes nodes : nodesList){
						Edges edges = new Edges();
						//edges.setId(1287162837);
						edges.setLabel("");
						edges.setType("Undirected");
						edges.setSource(nodes.getId());
						edges.setWeight(1);
						System.out.println("Current :" + nodes.toString());
						if(index < nodesList.size()-1){
							System.out.println("Next :" + nodesList.get(index+1));
							edges.setTarget(nodesList.get(index+1).getId());
							session.save(edges);
						}
						index++;
					}
					System.out.println(nodesList);
					System.out.println(s);
				}
				
				System.out.println("================================");
//			}
			k++;
			
		}
	}
	
	public void createMeshLinks(){
		
	}
}
