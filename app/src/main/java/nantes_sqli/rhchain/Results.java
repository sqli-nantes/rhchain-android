package nantes_sqli.rhchain;

import java.util.ArrayList;

/**
 * Created by alb on 22/11/16. Votes for a specific survey
 *
 * class to import data store in BC Not working at this time Parameters: User's list who had
 * answered Vote, containing array per questions id's question and answer's value
 */

public class Results {
    /**
     * the user who have answered
     */
    User user;
    /**
     * list of votes providing the identifiers of questions and answers
     */
    ArrayList<Vote> votes;
    /**
     * survey from which the results are display
     */
    Survey survey;
    Question questionId;

    public Results(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public Results() {
        this.votes = new ArrayList<Vote>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserById(String userId) {
        this.user = User.getUserById("demo");
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

//    public int nbUser() {
//        int number;
//        number = user.;
//        return number;
//    }

}
