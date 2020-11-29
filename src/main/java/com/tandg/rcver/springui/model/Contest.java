package com.tandg.rcver.springui.model;

import java.util.*;

/**
 * A class representing a ranked-choice voting (i.e. instant runoff) contest.
 */
public class Contest {
    /**
     * All of the Rounds of the Contest.
     */
    private ArrayList<Round> rounds = new ArrayList<>();

    /**
     * All of the Piles corresponding to each of the candidates in the Contest.
     * key = candidate name, value = corresponding pile
     */
    private HashMap<String, Pile> piles;

    /**
     * The names of the candidates that have not been eliminated yet.
     */
    private HashSet<String> activeCandidates;

    /**
     * The names of all of the candidates in the Contest.
     */
    private HashSet<String> controlCandidates;
    
    private HashSet<String> displayCandidates;
    
	public HashSet<String> getDisplayCandidates() {
		return displayCandidates;
	}

	/**
     * Whether the runContest method has already been called once.
     */
    private boolean contestRun = false;
    
    public Contest() {
        
    }

    public HashSet<String> getActiveCandidates() {
		return activeCandidates;
	}



	public void setActiveCandidates(HashSet<String> activeCandidates) {
		this.activeCandidates = activeCandidates;
	}



	public boolean isContestRun() {
		return contestRun;
	}



	public void setContestRun(boolean contestRun) {
		this.contestRun = contestRun;
	}



	public ArrayList<Round> getRounds() {
		return rounds;
	}



	public HashMap<String, Pile> getPiles() {
		return piles;
	}



	public HashSet<String> getControlCandidates() {
		return controlCandidates;
	}



	public void setRounds(ArrayList<Round> rounds) {
		this.rounds = rounds;
	}



	public void setPiles(HashMap<String, Pile> piles) {
		this.piles = piles;
	}



	public void setControlCandidates(HashSet<String> controlCandidates) {
		this.controlCandidates = controlCandidates;
	}



	public void setDisplayCandidates(HashSet<String> displayCandidates) {
		this.displayCandidates = displayCandidates;
	}

}
