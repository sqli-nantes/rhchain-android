package nantes_sqli.rhchain.utils;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;

import nantes_sqli.rhchain.data.BallotBox;
import nantes_sqli.rhchain.data.Question;
import nantes_sqli.rhchain.data.Answer;
import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.data.User;

/**
 * Created by alb on 19/01/17.
 * Class using for demo purpose
 */

public class Bouchonnage extends AppCompatActivity {

    static String[] questionsStr = {
        "Êtes-vous satisfait(e) de l'ambiance de travail au sein de l'agence ?",
        "Êtes-vous satisfait(e) de votre mission actuelle ?",
        "Êtes-vous satisfait(e) des informations qui vous sont transmises concernant la situation et les perspectives de l'agence et du groupe ?"
    };


    public Bouchonnage() {
    }

    public static Survey setDemoSurvey() {
        Survey survey = new Survey();
        survey.setId("Questionnaire_RH_demo");

        //Construction du type de reponse 3 choix
        List<Answer> threeChoicesAnswers = AnswersFactory.createAnswers(AnswersType.THREE_ANSWERS);

        //Construction des questions
        ArrayList<Question> questions = new ArrayList<>();

        // Construction des questions
        for(int i=0; i<questionsStr.length; i++) {
            Question question = new Question(String.valueOf(i), questionsStr[i], threeChoicesAnswers);
            questions.add(question);

        }

        survey.setId("1");
        survey.setLabel("Satisfaction travail");
        survey.setQuestions(questions);

        return survey;
    }

    public static User setDemoUser(){
        User user = new User();
        user.setIdentity("toto");
        return user;
    }

    public static BallotBox setDemoBallotBox() {


        BallotBox ballotbox = new BallotBox();
        return ballotbox;
    }
}
