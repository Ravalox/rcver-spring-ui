package com.tandg.rcver.springui.model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An object holding the ranked choices submitted by a user.
 */
public class Ballot {
    /**
     * An internal collection that holds the candidate names in ranked order.
     */
    private ArrayList<String> ballotArr;

    public void setBallotArr(ArrayList<String> ballotArr) {
		this.ballotArr = ballotArr;
	}

	public ArrayList<String> getBallotArr() {
		return ballotArr;
	}

	/**
     * Create a new Ballot.
     * @param rankedChoices The choices submitted by the user in the order they were ranked.
     */
    public Ballot(List<String> rankedChoices) {
        ballotArr = new ArrayList<>(rankedChoices);
    }

    /**
     * Get the number of candidates that were ranked by the user when they submitted their ballot.
     * @return The number of candidates ranked on the ballot.
     */
    public int getNumberRanked() {
        return ballotArr.size();
    }
}
