package com.tandg.rcver.springui.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tandg.rcver.springui.model.Contest;
import com.tandg.rcver.springui.service.RankedChoiceVoteService;

@RunWith(SpringRunner.class)
@WebMvcTest(VoteController.class)
public class VoteControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RankedChoiceVoteService rcvService;
	
	@Test
	public void givenVotes_determineWinner() throws Exception {
		Collection<String> candidates = new ArrayList<String>();
		candidates.add("test");
		System.out.println("test");
		candidates.add("test2");
		System.out.println("test2");
		Collection<List<String>> rawBallots = new ArrayList<List<String>>();
		ArrayList firstVote = new ArrayList();
		firstVote.add("test");
		rawBallots.add(firstVote);
		
        Contest contest = rcvService.createContest(candidates, rawBallots);
        
        assert(rcvService.getWinner(contest).contains("test"));
	}

}
