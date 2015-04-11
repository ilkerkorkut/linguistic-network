package com.linguistic.datacollector;

import com.linguistic.datacollector.config.AppProperties;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;

public class App {
	public static void main(String[] args) {
		
		@SuppressWarnings("deprecation")
		FacebookClient facebookClient = new DefaultFacebookClient(AppProperties.getProperty("facebook.accessToken"));
		
		Connection<Post> haberturkPosts = facebookClient.fetchConnection("Haberturk/posts", Post.class,Parameter.with("limit", "1"));
		Connection<Post> hurriyetPosts = facebookClient.fetchConnection("hurriyet/posts", Post.class,Parameter.with("limit", "1"));
		Connection<Post> trthaberPosts = facebookClient.fetchConnection("trthaber/posts", Post.class,Parameter.with("limit", "1"));
		Connection<Post> acunncomPosts = facebookClient.fetchConnection("acunncom/posts", Post.class,Parameter.with("limit", "1"));
		
//		System.out.println("TRTHABER");
//		DatacollectorComments.save("trthaber", trthaberPosts, facebookClient);
//		System.out.println("HABERTURK");
//		DatacollectorComments.save("Haberturk", haberturkPosts, facebookClient);
		System.out.println("HURRIYET");
		DatacollectorComments.save("hurriyet", hurriyetPosts, facebookClient);
		System.out.println("ACUNNCOM");
		DatacollectorComments.save("acunncom", acunncomPosts, facebookClient);
		
		System.out.println("========DATACOLLECT FINISHED========");
		
	}
}
