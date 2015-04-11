package com.linguistic.datacollector;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;

public class DataCollectionLogics {
	public static List<String> getCommentsFromPostById(FacebookClient client, String id){
		List<String> comments = new ArrayList<String>();
		
		Connection<Comment> allComments = client.fetchConnection(id+"/comments", Comment.class, Parameter.with("limit", "999"));

		for(List<Comment> postcomments : allComments){
			if(postcomments != null){
		        for (Comment comment : postcomments){
		        	comments.add(comment.getMessage());
		        }
			}
	    }
		return comments;
	}
}
