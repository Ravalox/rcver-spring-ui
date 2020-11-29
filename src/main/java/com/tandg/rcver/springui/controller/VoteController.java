package com.tandg.rcver.springui.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tandg.rcver.springui.model.Contest;
import com.tandg.rcver.springui.presentationobject.BallotPO;

@Controller
public class VoteController {
	
	@Autowired
	Contest contest;

	@GetMapping("vote")
    public String electionLanding (Map<String, Object> model) {
		BallotPO ballotPO = createBallotPO(contest);
		model.put("ballot", ballotPO);
        model.put("message", "Hello Thymeleaf");
        return "thyme";
    }
	
	@PostMapping("vote")
    public String castVote (BallotPO ballotPO, BindingResult result) {
		System.out.println("Vote cast.");
        return "redirect:vote";
    }
	/*
	@PostConstruct
    public void init() {
		
		Collection<String> candidates = new ArrayList<String>();
		candidates.add("test");
		candidates.add("test2");
		Collection<List<String>> rawBallots = new ArrayList<List<String>>();
		
		
        contest = new Contest(candidates, rawBallots);
    }*/
	
	public BallotPO createBallotPO(Contest contest) {
		BallotPO ballotPO = new BallotPO();
		ballotPO.setCandidates(new ArrayList<String>());
		
		if(contest != null && contest.getDisplayCandidates() != null) {
			contest.getDisplayCandidates().forEach (e -> ballotPO.getCandidates().add(e) );
		}
		
		//reversing because Hashmap iteration starts in the other direction.
		//will reexamine this approach in favor of iterating in reverse at a later date
		Collections.reverse(ballotPO.getCandidates());
		
		return ballotPO;
	}
}
