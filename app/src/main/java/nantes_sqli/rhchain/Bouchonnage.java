package nantes_sqli.rhchain;

import android.util.Log;

import java.sql.CallableStatement;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alb on 19/01/17.
 * Class using for demo purpose
 */

public class Bouchonnage extends AppCompatActivity {

    Survey survey = new Survey();
    User user = new User();
    BallotBox ballotbox = new BallotBox();

    public Bouchonnage(Survey survey) {
        this.survey = survey;
    }

    public Bouchonnage() {
    }

    public Survey setDemoSurvey() {

        Log.v("méthode", "importation du survey");
        survey.setId("Questionnaire_RH_demo");

        Answer satisfied = new Answer();
        Answer verySatisfied = new Answer();
        Answer unsatisfied = new Answer();

        ArrayList<Answer> answers = new ArrayList<>();


        unsatisfied.setId("1");
        unsatisfied.setDescription(getResources().getString(R.string.descr_btn_unsatisfied));
        unsatisfied.setValue(0);
        unsatisfied.setNameImage(String.valueOf(R.drawable.ic_sentiment_dissatisfied_black_24dp));
        satisfied.setId("2");
        satisfied.setDescription(getResources().getString(R.string.descr_btn_neutral));
        satisfied.setValue(1);
        satisfied.setNameImage(String.valueOf(R.drawable.ic_sentiment_neutral_black_24dp));
        verySatisfied.setId("3");
        verySatisfied.setDescription(getResources().getString(R.string.descr_btn_satisfied));
        verySatisfied.setValue(2);
        verySatisfied.setNameImage(String.valueOf(R.drawable.ic_sentiment_satisfied_black_48dp));

        answers.add(unsatisfied);
        answers.add(satisfied);
        answers.add(verySatisfied);

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();

        ArrayList<Question> questions = new ArrayList<>();
        question1.setId("1");
//        question1.setTextQuestion(getResources().getString(R.string.txt_question1));
        question1.setTextQuestion(getResources().getString(R.string.txt_question1));
        question1.setAnswers(answers);

        question2.setId("2");
        question2.setTextQuestion(getResources().getString(R.string.txt_question2));
        question2.setAnswers(answers);

        question3.setId("3");
        question3.setTextQuestion(getResources().getString(R.string.txt_question3));
        question3.setAnswers(answers);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        survey.setId("1");
        survey.setLabel(getResources().getString(R.string.txt_ttl_survey));
        survey.setQuestions(questions);

        return survey;
    }

    public User setDemoUser(){
        user.setIdentity("toto");
        return user;
    }

    public BallotBox setDemoBallotBox() {

        return ballotbox;
    }
}
