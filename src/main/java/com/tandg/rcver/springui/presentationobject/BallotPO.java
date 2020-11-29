package com.tandg.rcver.springui.presentationobject;

import java.util.ArrayList;

public class BallotPO {

	private ArrayList<String> candidates;
	
	private ArrayList<String> rankedCandidates;

	public ArrayList<String> getCandidates() {
		return candidates;
	}

	public void setCandidates(ArrayList<String> candidates) {
		this.candidates = candidates;
	}

	public ArrayList<String> getRankedCandidates() {
		return rankedCandidates;
	}

	public void setRankedCandidates(ArrayList<String> rankedCandidates) {
		this.rankedCandidates = rankedCandidates;
	}	
	
}
