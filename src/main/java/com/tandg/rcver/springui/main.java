package com.tandg.rcver.springui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tandg.rcver.springui.model.Contest;
import com.tandg.rcver.springui.service.RankedChoiceVoteServiceImpl;

public class main {

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection<String> candidates = new ArrayList<String>();
		candidates.add("test");
		System.out.println("test");
		candidates.add("test2");
		System.out.println("test2");
		Collection<List<String>> rawBallots = new ArrayList<List<String>>();
		ArrayList firstVote = new ArrayList();
		firstVote.add("test");
		rawBallots.add(firstVote);
		
		RankedChoiceVoteServiceImpl rcvService = new RankedChoiceVoteServiceImpl();
		
        Contest contest = rcvService.createContest(candidates, rawBallots);
        
        System.out.println(rcvService.getWinner(contest));
        
	}

}
