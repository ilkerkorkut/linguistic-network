package com.linguistic.network;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.linguistic.network.entity.Edges;
import com.linguistic.network.entity.Nodes;

public class Graphlogic {
	
	private Session session;
	private String redisSetKey;
	private JedisFactory jf;
	
	public Graphlogic(JedisFactory jf, Session session, String redisSetKey){
		this.session = session;
		this.jf = jf;
		this.redisSetKey = redisSetKey;
	}
	
	public void createGraphByLinkType(String linkType, int limit){
		int k = 0;
		for(String s: jf.getRedisSet(redisSetKey)){
			s = s.replaceAll(Constants.CLEAR_SENTENCE, "");
			if(k<limit && s.length() > 10){
				String[] sentence = s.split(" ");
				List<Nodes> nodesList = getNodesList(sentence);
				if(linkType == Constants.LINK_SEQUENTIAL){					
					saveSequential(nodesList);
				}else if(linkType == Constants.LINK_MESH){
					saveMesh(nodesList);
				}
			}
			k++;
		}
	}
	
	private List<Nodes> getNodesList(String[] sentence){
		List<Nodes> nodesList = new ArrayList<Nodes>();
		for(int i=0;i < sentence.length; i++){
			Nodes node = new Nodes();
			node.setLabel(sentence[i]);
			node.setNodes(sentence[i]);
			nodesList.add(node);
			session.save(node);
		}
		return nodesList;
	}
	
	private void saveMesh(List<Nodes> nodesList){
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
	
	private void saveSequential(List<Nodes> nodesList){
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
