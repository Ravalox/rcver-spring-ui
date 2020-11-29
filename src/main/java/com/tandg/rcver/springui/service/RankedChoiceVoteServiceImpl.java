package com.tandg.rcver.springui.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tandg.rcver.springui.model.Ballot;
import com.tandg.rcver.springui.model.Contest;
import com.tandg.rcver.springui.model.Pile;
import com.tandg.rcver.springui.model.Round;

@Service
public class RankedChoiceVoteServiceImpl implements RankedChoiceVoteService {

	 /**
     * Get the name of the active candidate for which this Ballot is voting.
     * @param activeCandidates All of the currently active candidates.
     * @return The name of the highest-ranked, non-eliminated candidate on this Ballot.
     */
	@Override
	public String getVote(Ballot ballot, Collection<String> activeCandidates) {
		String vote = "";  // If all candidates have been eliminated, this ballot does not have a vote
        // Get the candidates on the ballot who are active
        ArrayList<String> activeCandidatesOnThisBallot = (ArrayList<String>) ballot.getBallotArr().clone();
        activeCandidatesOnThisBallot.retainAll(activeCandidates);  // should preserve order
        // If not all candidates on this Ballot have been eliminated
        if (!activeCandidatesOnThisBallot.isEmpty()) {
            // Return the highest-ranked candidate
            vote = activeCandidatesOnThisBallot.get(0);
        }
        // Return result
        return vote;
	}

	/**
     * Create and initialize a new RCV Contest.
     * @param candidates The names of all of the candidates to be included in the Contest.
     * @param rawBallots A collection of lists of candidate names in the order they
     *                   were ranked.
     */
	@Override
	public Contest createContest(Collection<String> candidates, Collection<List<String>> rawBallots) {
        Contest contest = new Contest();
		// Store the set of candidates
        contest.setControlCandidates(new HashSet<>(candidates));
        // Activate all of the candidates
        contest.setActiveCandidates(new HashSet<>(candidates));
        
        contest.setDisplayCandidates(new HashSet<>(candidates));
        // Create a Pile for each candidate
        contest.setPiles(new HashMap<>(candidates.size()));
        Pile pile;
        for (String candidate : candidates) {
            // Create the Pile
            pile = new Pile(candidate);
            // Add it to the collection of Piles
            contest.getPiles().put(candidate, pile);
        }
        // Create the Pile initially containing all of the Ballots
        Pile initialPile = new Pile("__initialPile");
        Ballot ballot;
        // For each raw ballot
        for (List<String> rawBallot : rawBallots) {
            // Make a corresponding Ballot
            ballot = new Ballot(rawBallot);
            // Add it to the initial Pile
            initialPile.addBallot(ballot);
        }
        // Add the initial Pile to the collection of Piles
        contest.getPiles().put("__initialPile", initialPile);
        // Add __initalPile to the list of candidates (but NOT the list of active candidates)
        contest.getControlCandidates().add("__initialPile");
        
        return contest;
    }

	@Override
	/**
     * Runs the RCV algorithm with the Ballots and candidates given at
     * construction.
     */
    public Contest runContest(Contest contest) {
        contest.setContestRun(true);
        // Distribute the initial Pile of Ballots
        contest = eliminateCandidates(contest);
        Round round;
        do {
            // Start a new Round
            round = createNewRound(contest.getPiles());
            // Add it to the list of rounds
            contest.getRounds().add(round);
            // Update the list of active candidates
            contest.setActiveCandidates(getNonEliminatedCandidates(round));
            // Eliminate candidates that are no longer active
            contest = eliminateCandidates(contest);
        // Repeat until all candidates have been eliminated
        } while (!contest.getActiveCandidates().isEmpty());
        
        return contest;
    }

	@Override
	public Round createNewRound(HashMap<String, Pile> piles) {
		Round round = new Round(new HashMap<>(piles.size()));
        String name;
        int votes;
        // For each pile
        for (Map.Entry<String,Pile> entry : piles.entrySet())  
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue()); 
        
        for (Map.Entry<String,Pile> pile : piles.entrySet()) {
        	if("__initialPile".equals(pile.getKey())){
        		continue;
        	}
            // Get the name of the candidate
        	System.out.println(pile.getKey());
            name = piles.get(pile.getKey()).getName();
            // Get the number of ballots/votes in the pile
            votes = piles.get(pile.getKey()).getTotalBallots();
            // Store the number of votes with the name of the candidate
            round.getVoteDistribution().put(name, votes);
        }
        
        return round;
    }

	/**
     * Redistribute the Ballots of any candidates that are no longer active.
     */
	@Override
	public Contest eliminateCandidates(Contest contest) {
        Pile pile;
        // For each candidate
        for (String candidate : contest.getControlCandidates()) {
            // If the candidate has been eliminated, i.e. is no longer active
            if (!contest.getActiveCandidates().contains(candidate)) {
                // Redistribute the Ballots of its corresponding Pile
                pile = contest.getPiles().get(candidate);
                contest = redistributeBallots(contest, pile);
            }
        }
        
        return contest;
    }

	/**
     * Redistributes the Ballots in the given Pile to the Piles of the active
     * candidates.
     * @param pile The Pile whose Ballots are to be redistributed.
     */
	@Override
	public Contest redistributeBallots(Contest contest, Pile pile) {
        String vote;
        Pile votePile;
        // For each Ballot in the Pile
        for (Ballot ballot : pile) {
            // Get the candidate for which the Ballot is voting
        	vote = getVote(ballot, contest.getActiveCandidates());
            // If this Ballot is casting a vote
            if (!vote.isEmpty()) {
                // Get the corresponding Pile
                votePile = contest.getPiles().get(vote);
                // Add the Ballot to the Pile
                votePile.addBallot(ballot);
            }
            // Else the Ballot is ignored
        }
        
        return contest;
    }

	/**
     * Get the distributions of votes among the candidates for each Round.
     * @return The distributions of votes in each Round ordered by Round.
     */
	@Override
	public ArrayList<HashMap<String, Integer>> getVoteDistributions(Contest contest) {
        // If the contest has not already been run once
        if (!contest.isContestRun()) {
            // Run the contest
            contest = runContest(contest);
        }
        ArrayList<HashMap<String, Integer>> voteDistributions = new ArrayList<>(contest.getRounds().size());
        HashMap<String, Integer> voteDist;
        // For each Round in the Contest
        for (Round round : contest.getRounds()) {
            // Get the vote distribution of the Round
            voteDist = round.getVoteDistribution();
            // Add it to the list of vote distributions
            voteDistributions.add(voteDist);
        }
        // Return the list of vote distributions
        return voteDistributions;
    }

	 /**
     * Get the winner of the Contest. The winner is the candidate that got the
     * most votes in the last Round. If two or more candidates tie for first in
     * the last Round, the candidate among them that got the most votes in the
     * second-to-last Round is the winner. If there is a tie among them in the
     * second-to-last Round, the process repeats until a single winner is found. If no
     * single winner is found through this process, then they all tie for winner.
     * @return The winner(s) of the Contest. Contains multiple winners in the case of a tie.
     */
	@Override
	public HashSet<String> getWinner(Contest contest) {
        // If the contest has not already been run once
        if (!contest.isContestRun()) {
            // Run the contest
            contest = runContest(contest);
        }
        // Initialize the tracker of the top candidates
        HashSet<String> topCandidates = (HashSet<String>) contest.getControlCandidates().clone();
        // Initialize the Round tracker
        int roundNum = contest.getRounds().size() - 1;
        Round currentRound;
        // While there is a tie and there are still Rounds left to check
        while ((topCandidates.size() != 1) && (roundNum >= 0)) {
            // Get the Round currently being analyzed
            currentRound = contest.getRounds().get(roundNum);
            // Get the candidates among the top candidates with the most votes
            // in this round; this is the new list of top candidates
            topCandidates = findTopCandidates(currentRound, topCandidates);
            // Set next iteration to consider the next Round
            roundNum--;
        }
        // The set of top candidates has been narrowed down to the winner(s); return them
        return topCandidates;
    }

	/**
     * Get the number of votes of a particular candidate in this Round. Any
     * candidate not in this Round will report as having zero votes.
     * @param candidate The name of the candidate.
     * @return The total number of votes in this Round for the candidate.
     */
	@Override
	public int getVotes(Round round, String candidate) {
        int votes = 0;  // If the candidate cannot be found in this Round for whatever reason, they have 0 votes
        // If the candidate is in this Round
        if (round.getVoteDistribution().containsKey(candidate)) {
            // Get their total number of votes
            votes = round.getVoteDistribution().get(candidate);
        }
        // Return vote total
        return votes;
    }

	/**
     * Get the names of the candidates who were not eliminated this Round, i.e.
     * the candidates who did not have the least number of votes.
     * @return The set of the names of the candidates who were not eliminated.
     */
	@Override
	public HashSet<String> getNonEliminatedCandidates(Round round) {
        Collection<Integer> voteTotals = round.getVoteDistribution().values();
        int minVotes = voteTotals.stream().min(Comparator.naturalOrder()).get();
        Set<String> candidates = round.getVoteDistribution().keySet();
        HashSet<String> nonEliminatedCandidates = new HashSet<>();
        int votes;
        for (String candidate : candidates) {
            votes = round.getVoteDistribution().get(candidate);
            if (votes != minVotes) {
                nonEliminatedCandidates.add(candidate);
            }
        }
        return nonEliminatedCandidates;
    }

	/**
     * Get the candidate(s) among the given candidates with the most votes in
     * this Round.
     * @param restriction A (nonempty) collection of candidates.
     * @return The set of top candidates among the given candidates.
     */
	@Override
	public HashSet<String> findTopCandidates(Round round, Collection<String> restriction) {
        if (restriction.isEmpty()) {
            return new HashSet<>(Set.of());
        }
        HashMap<String, Integer> voteDistRestricted = (HashMap<String, Integer>) round.getVoteDistribution().clone();
        for (String candidate : round.getVoteDistribution().keySet()) {
            if (!restriction.contains(candidate)) {
                voteDistRestricted.remove(candidate);
            }
        }
        Collection<Integer> voteTotals = voteDistRestricted.values();
        int maxVotes = voteTotals.stream().max(Comparator.naturalOrder()).get();
        Set<String> candidates = voteDistRestricted.keySet();
        HashSet<String> topCandidates = new HashSet<>();
        int votes;
        for (String candidate : candidates) {
            votes = voteDistRestricted.get(candidate);
            if (votes == maxVotes) {
                topCandidates.add(candidate);
            }
        }
        return topCandidates;
    }

}
