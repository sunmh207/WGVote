package com.stanley;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stanley.vote.service.VoteService;

public class WGVoteTest {
	public static void main(String[] args) throws Exception{
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		VoteService s = (VoteService) context.getBean("voteService");
		s.deleteAll("from Voter");		
		
		context.close();
	}
}
