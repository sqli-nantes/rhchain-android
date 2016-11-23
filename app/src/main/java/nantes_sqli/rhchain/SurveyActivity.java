package nantes_sqli.rhchain;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//public class SurveyActivity extends AppCompatActivity implements View.OnClickListener{
//    public class SurveyActivity extends FragmentActivity implements View.OnClickListener {
    public class SurveyActivity extends FragmentActivity {
//    public class SurveyActivity extends Fragment implements View.OnClickListener {
    Button btn_sub;
//    Survey survey;
//    Results results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_redone);


        btn_sub = (Button) findViewById(R.id.btn_soum);

//        btn_sub.setOnClickListener(this);

//        TODO: utiliser GridView pour ajuster l'affichage aux questions.
/*        GridView gridAnswer = (GridView)findViewById(R.id.gridview1);
        });
        survey = (Survey) savedInstanceState.getSerializable("Survey");


        TODO faire l'unité minimum de la question voir TextView et TableLayout
        for (int i = 1; i < survey.getQuestions().size();i++ ) {
            TableRow globalrow = new TableRow(this);
            Question question = survey.getQuestion(i);
            TextView textView = new TextView(this);
            textView.setText(question.getTextQuestion());

            for (int i = 1 ; i < question.getAnswers().size(); i++){
                globalrow.;
                globalrow.addView(question.getAnswer(i));
            }


*/
    }

//    public class GridSurvey extends Activity implements AdapterView.OnItemSelectedListener{
//      Pour la gestion de la gridview
//    }

//
//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent(this, ResultsFragment);
//        startActivity(intent);
//    }
//
//    public void onSurveySubmit(Results r) {
//
//    }


//    TODO Gestion des réponses. Icônes : Au départ : grisée, quand sélectionné mise en couleurs + Results renseigné par la valeurs answer
//    TODO 
//    TODO Fil d'ariane
}
