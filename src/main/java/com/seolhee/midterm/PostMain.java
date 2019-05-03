package com.seolhee.midterm;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"post-context.xml");
		PostService postService = context.getBean("postService",
				PostService.class);

		postService.post();
		postService.list();
		postService.like();
		context.close();
	}

}