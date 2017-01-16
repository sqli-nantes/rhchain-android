package nantes_sqli.rhchain;

import java.util.ArrayList;

/**
 * Created by alb on 10/01/17.
 */

public class BallotBox {
    /**
     * number of people who have vote
     */
    int nbVoters;

    /**
     * Check if the number of voters is equal to  number of vote
     */
    boolean isValid;
    /**
     * number of votes
     */
//    int NbVotes;


    ArrayList<CountVote> countVotes;



    public int getNbVoters() {
        return nbVoters;
    }

//    public int getNbVotes() {
//        return NbVotes;
//    }

//    public CountVote getCountVotes() {
//        return countVotes;
//    }

    public boolean isValid() {
        isValid = false;
        for (CountVote count : countVotes){
            int sumVote = count.sumAnswers;
            if (sumVote == nbVoters) {
                isValid = true;
            }
            else {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
