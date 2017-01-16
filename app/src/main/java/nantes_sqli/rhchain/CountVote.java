package nantes_sqli.rhchain;

import java.util.ArrayList;

/**
 * Created by alb on 10/01/17.
 */
public class CountVote {
    String questionId ;
    ArrayList<Integer> answersCount;
    int sumAnswers;

    public CountVote() {
    }

    public String getQuestionId(String label) {
        return questionId;
    }

    public ArrayList<Integer> getAnswersCount() {
        return answersCount;
    }

    public int getSumAnswers() {
        sumAnswers = sum(answersCount);
        return sumAnswers;
    }

    private int sum(ArrayList<Integer> list){
        int sum = 0;

        for (int nb : list) {
            sum = sum + nb;
        }
        return sum;
    }
}
