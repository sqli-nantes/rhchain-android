package nantes_sqli.rhchain;

import java.util.ArrayList;
import java.util.List;

import nantes_sqli.rhchain.data.Answer;

/**
 * Created by mcame on 02/02/17.
 */

public class AnswersFactory {

    public static List<Answer> createAnswers(AnswersType type){
        ArrayList<Answer> answers = new ArrayList<>();
        //Construction du type de reponse 3 choix
        switch (type){
            case THREE_ANSWERS :
                answers.addAll(threeAnswersBuild());
                break;

            default:
                break;

        }

        return answers;
    }


    private static List<Answer> threeAnswersBuild(){
        ArrayList<Answer> answers = new ArrayList<>();

        Answer unsatisfied      = new Answer("1", "insatisfait",    0,  R.drawable.ic_sentiment_dissatisfied_black_24dp,R.drawable.ic_dissatisfied_clicked);
        Answer satisfied        = new Answer("2", "satisfait",      1,  R.drawable.ic_sentiment_neutral_black_24dp,     R.drawable.ic_neutral_clicked);
        Answer verySatisfied    = new Answer("3", "très satisfait", 2,  R.drawable.ic_sentiment_satisfied_black_48dp,   R.drawable.ic_satisfied_clicked);

        answers.add(unsatisfied);
        answers.add(satisfied);
        answers.add(verySatisfied);

        return answers;
    }

}
