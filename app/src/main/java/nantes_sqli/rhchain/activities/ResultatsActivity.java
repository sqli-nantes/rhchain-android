package nantes_sqli.rhchain.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.data.QuestionResultat;
import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.utils.Bouchonnage;
import nantes_sqli.rhchain.utils.QuestionResultatAdapter;

public class ResultatsActivity extends AppCompatActivity {
    public static final int LARGEUR_MINIMALE = 50;

    private ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);
        Integer[] listeResultatsQ1 = new Integer[]{1, 400, 30};
        Integer[] listeResultatsQ2 = new Integer[]{40, 30, 20};
        Integer[] listeResultatsQ3 = new Integer[]{10, 10, 10};

        Integer[][] listeResultats = new Integer[][]{listeResultatsQ1, listeResultatsQ2, listeResultatsQ3};
        Survey survey = Bouchonnage.setDemoSurvey();
        String[] listeQuestions = new String[]{survey.getQuestion(0).getTextQuestion(),survey.getQuestion(1).getTextQuestion(),survey.getQuestion(2).getTextQuestion()};



        initResultats(listeQuestions,listeResultats);
    }

    private void initResultats(String[] listeQuestions, Integer[][] resultsSurvey) {
        mlistView = (ListView) findViewById(R.id.listequestionResultats);
        //generer les modele de questionsResultat
        final List<QuestionResultat> allQuestionResponses = new ArrayList<>();
        for (int i = 0; i < listeQuestions.length; i++) {
            allQuestionResponses.add(new QuestionResultat(listeQuestions[i], resultsSurvey[i]));
        }
        QuestionResultatAdapter adapter = new QuestionResultatAdapter(ResultatsActivity.this, allQuestionResponses);
        mlistView.setAdapter(adapter);
    }

}
