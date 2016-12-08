package nantes_sqli.rhchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Display 2 possibilities: answer survey or create a user's account At btn_connect get the survey
 * to be displayed Use Survey class User class
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_connect, btn_account;
    Survey survey = new Survey();
    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_connect = (Button) findViewById(R.id.btn_connect);
        setSurvey(survey);
        btn_connect.setOnClickListener(this);

        user.setIdentity("toto");


/*      Utilisable lors de la mise en prod de la fonction reaction compte
        btn_account = (Button) findViewById(R.id.btn_account);
        btn_account.setOnClickListener(this);
*/

    }

//           todo récupérer les informations users
//                startActivity(intent);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect: {

                Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);

/*                doit récupérer les informations users
                user.setIdentity();
                doit vérifier que le password entré est identique aux password enregistrer par la BC
                if (R.id.editTextPassword = user.getPassword()){

                }
*/
                startActivity(intent);
                break;
            }
/*            case R.id.btn_account: {
                Log.v("étape","choix de la création");
                Intent intent = new Intent(this, CreateUserActivity.class);
                startActivity(intent);
                break;
            }
 */
            default:
                break;

        }
    }


/*    public Survey getSurvey{
        Survey survey;
        String id;
        String label;
        List<Question> questions;

        id = survey.getId();
        label = survey.getLabel();
        questions = survey.getQuestion();
    }
*/


    //    Bouchonnage pour le sondage démo. Déclaration du sondage
    public Survey setSurvey(Survey survey) {

        Log.v("méthode", "importation du survey");
        survey.setId("Questionnaire_RH_demo");

        Answer satisfied = new Answer();
        Answer verySatisfied = new Answer();
        Answer unsatisfied = new Answer();

        ArrayList<Answer> answers = new ArrayList<>();


        unsatisfied.setId("1");
        unsatisfied.setDescription(String.valueOf(R.string.descr_btn_unsatisfied));
        unsatisfied.setValue(1);
        unsatisfied.setNameImage(String.valueOf(R.drawable.ic_sentiment_dissatisfied_black_24dp));
        satisfied.setId("2");
        satisfied.setDescription(String.valueOf(R.string.descr_btn_neutral));
        satisfied.setValue(2);
        satisfied.setNameImage(String.valueOf(R.drawable.ic_sentiment_neutral_black_24dp));
        verySatisfied.setId("3");
        verySatisfied.setDescription(String.valueOf(R.string.descr_btn_satisfied));
        verySatisfied.setValue(1);
        verySatisfied.setNameImage(String.valueOf(R.drawable.ic_sentiment_satisfied_black_48dp));

        answers.add(unsatisfied);
        answers.add(satisfied);
        answers.add(verySatisfied);

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();

        ArrayList<Question> questions = new ArrayList<>();
        question1.setId("1");
        question1.setTextQuestion(String.valueOf(R.string.txt_question1));
        question1.setAnswers(answers);

        question2.setId("2");
        question2.setTextQuestion(String.valueOf(R.string.txt_question2));
        question2.setAnswers(answers);

        question3.setId("3");
        question3.setTextQuestion(String.valueOf(R.string.txt_question3));
        question3.setAnswers(answers);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        survey.setId("1");
        survey.setLabel(String.valueOf(R.string.txt_ttl_survey));
        survey.setQuestions(questions);

        return survey;
    }

}

