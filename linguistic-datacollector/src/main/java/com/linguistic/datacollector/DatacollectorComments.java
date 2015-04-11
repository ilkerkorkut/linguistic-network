package com.linguistic.datacollector;

import java.util.List;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.types.Post;

public class DatacollectorComments {

	public static void save(String pageName, Connection<Post> pagePosts, FacebookClient facebookClient){
		JedisFactory jedisFactory = new JedisFactory();
		for (List<Post> posts : pagePosts){
		    for (Post post : posts){
		        String id      = post.getId();
		        System.out.println("--PostId : " +id+ " ---");
		        DataCollectionLogics.getCommentsFromPostById(facebookClient, id);
		        for(String comment : DataCollectionLogics.getCommentsFromPostById(facebookClient, id)){
		        	System.out.println("JEDIS : " + comment);
		        	jedisFactory.addToSet(pageName, comment);
		        }
		                
		    }
		}
	}
	
}
