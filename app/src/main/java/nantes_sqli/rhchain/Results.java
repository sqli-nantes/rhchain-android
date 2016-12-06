package nantes_sqli.rhchain;

import java.util.ArrayList;

/**
 * Created by alb on 22/11/16. Class of votes for the questions displayed
 *
 * Need : User client who had answered Vote containing array per questions id's question and
 * answer's value
 */

public class Results {
    User user;
    ArrayList<Vote> votes;

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

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }
}
