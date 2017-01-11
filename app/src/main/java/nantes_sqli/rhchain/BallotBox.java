package nantes_sqli.rhchain;

/**
 * Created by alb on 10/01/17.
 */

public class BallotBox {
    /**
     * number of people who have vote
     */
    int nbVoters;
    /**
     * number of votes
     */
    int NbVotes;

    CountVote countVotes;

    public int getNbVoters() {
        return nbVoters;
    }

    public int getNbVotes() {
        return NbVotes;
    }

    public CountVote getCountVotes() {
        return countVotes;
    }

}
