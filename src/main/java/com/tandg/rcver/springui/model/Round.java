package com.tandg.rcver.springui.model;

import java.util.*;
import java.util.stream.Stream;

/**
 * A class representing the vote distributions of a round of a Contest.
 */
public class Round {
    /**
     * The distribution by candidate name of the votes for this Round
     */
    private final HashMap<String, Integer> voteDistribution;

    /**
     * Create a new Round of the Contest.
     * @param piles A list of the Piles of the candidates in the Contest.
     */
    public Round(HashMap<String, Integer> distribution) {
        voteDistribution = distribution;
    }

    /**
     * Get the number of votes for each candidate this Round.
     * @return A copy of the distribution of the votes over all candidates.
     */
    public HashMap<String, Integer> getVoteDistribution() {
        return (HashMap<String, Integer>) voteDistribution.clone();
    }


}
