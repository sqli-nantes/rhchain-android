package nantes_sqli.rhchain;

import java.util.ArrayList;

/**
 * Created by alb on 10/01/17.
 */
public class CountVote {
    String questionId ;
    ArrayList<Integer> AnswersCount;
    int SumAnswers;

    public CountVote() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public ArrayList<Integer> getAnswersCount() {
        return AnswersCount;
    }

    public int getSumAnswers() {
        SumAnswers = sum(AnswersCount);
        return SumAnswers;
    }

    private int sum(ArrayList<Integer> list){
        int sum = 0;
//        for (int i = 0; i<3; i++ ){
//            sum = sum + list.get(i);
//        }
        for (int nb : list) {
            sum = sum + nb;
        }
        return sum;
    }
}
