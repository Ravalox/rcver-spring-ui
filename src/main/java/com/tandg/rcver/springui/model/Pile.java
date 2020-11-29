package com.tandg.rcver.springui.model;

import java.util.*;
import java.util.function.Consumer;

/**
 * A class representing the collection (pile) of Ballots cast for a candidate in
 * the contest.
 */
public class Pile implements Iterable<Ballot> {
    /**
     * The name of the candidate corresponding to this Pile of Ballots.
     */
    private String name;

    /**
     * The Ballots in this Pile.
     */
    private HashSet<Ballot> ballots = new HashSet<>();

    /**
     * Creates a new Pile of Ballots with the given name of the candidate to which this Pile
     * will correspond.
     */
    public Pile(String candidateName) {
        name = candidateName;
    }

    /**
     * Get the name of the candidate corresponding to this Pile of Ballots.
     * @return The name of the Candidate.
     */
    public String getName() {
        return name;
    }

    public HashSet<Ballot> getBallots() {
		return ballots;
	}

	public void setBallots(HashSet<Ballot> ballots) {
		this.ballots = ballots;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * Add a single Ballot to this Pile. If a Ballot already exists in this Pile, it
     * will not be added again.
     * @param newBallot The Ballot to be added to this Pile.
     */
    public void addBallot(Ballot newBallot) {
        ballots.add(newBallot);
    }

    /**
     * Add a collection of Ballots to this Pile. If a Ballot already exists in this Pile,
     * it will not be added again.
     * @param newBallots The collection of Ballots to be added to this Pile.
     */
    public void addAllBallots(Collection<Ballot> newBallots) {
        ballots.addAll(newBallots);
    }

    /**
     * Get the number of Ballots in this Pile.
     * @return The total number of Ballots in this Pile.
     */
    public int getTotalBallots() {
        return ballots.size();
    }


    /* Iterable interface implementation */

    @Override
    public void forEach(Consumer<? super Ballot> action) {
        ballots.forEach(action);
    }

    @Override
    public Iterator<Ballot> iterator() {
        return ballots.iterator();
    }

    @Override
    public Spliterator<Ballot> spliterator() {
        return ballots.spliterator();
    }

}
