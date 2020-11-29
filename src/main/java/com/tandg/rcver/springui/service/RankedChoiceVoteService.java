package com.tandg.rcver.springui.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tandg.rcver.springui.model.Ballot;
import com.tandg.rcver.springui.model.Contest;
import com.tandg.rcver.springui.model.Pile;
import com.tandg.rcver.springui.model.Round;

public interface RankedChoiceVoteService {
	
	//Ballot methods
	public String getVote(Ballot ballot, Collection<String> activeCandidates);
	
	//Contest Methods
	public Contest createContest(Collection<String> candidates, Collection<List<String>> rawBallots);
	
	public Contest runContest(Contest contest);
	
	public Round createNewRound(HashMap<String, Pile> piles);
	
	/**
     * Redistribute the Ballots of any candidates that are no longer active.
     */
	public Contest eliminateCandidates(Contest contest);
	
	public Contest redistributeBallots(Contest contest, Pile pile);
	
	public ArrayList<HashMap<String, Integer>> getVoteDistributions(Contest contest);
	
	public HashSet<String> getWinner(Contest contest);
	
	//Round methods
	public int getVotes(Round round, String candidate);
	
	public HashSet<String> getNonEliminatedCandidates(Round round);
	
	public HashSet<String> findTopCandidates(Round round, Collection<String> restriction);
	
	

}
