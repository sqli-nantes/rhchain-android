package nantes_sqli.rhchain.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sqli.blockchain.android_geth.EthereumService;

import java.util.ArrayList;
import java.util.List;

import ethereumjava.EthereumJava;
import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.RhchainApplication;
import nantes_sqli.rhchain.data.Question;
import nantes_sqli.rhchain.data.QuestionResultat;
import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.utils.Bouchonnage;
import nantes_sqli.rhchain.utils.QuestionResultatAdapter;

public class ResultatsActivity extends AppCompatActivity implements EthereumService.EthereumServiceInterface {
    public static final int LARGEUR_MINIMALE = 50;

    private ListView mlistView;
    public EthereumJava ethereumjava;
    RhchainApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);

        application = (RhchainApplication) getApplication();
        application.registerGethReady(this);

        //MOck results
//        MockedResults mockedResults = new MockedResults().invoke();
//        String[] listeQuestions = mockedResults.getListeQuestions();
//        Integer[][] listeResultats = mockedResults.getListeResultats();
//
//        //Show results
//        initResultats(listeQuestions, listeResultats);
    }

    private void initResultats(List listeQuestions, List<Integer[]> resultsValues) {
        mlistView = (ListView) findViewById(R.id.listequestionResultats);
        //generer les modele de questionsResultat
        final List<QuestionResultat> allQuestionResponses = new ArrayList<>();
        for (int i = 0; i < listeQuestions.size() && i <resultsValues.size(); i++) {
            allQuestionResponses.add(new QuestionResultat((String) listeQuestions.get(i), resultsValues.get(i)));
        }
        QuestionResultatAdapter adapter = new QuestionResultatAdapter(ResultatsActivity.this, allQuestionResponses);
        mlistView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (application.gethManager != null) {
            loadResults();
        }

    }

    @Override
    public void onEthereumServiceReady() {

        loadResults();

    }

    private void loadResults() {

        //Retrieve results from Blockchain API
        ArrayList<Integer[]> resultsList = application.gethManager.getResults(null);


        // TODO Récuperer libellés des questions sur la blockchain
        // Aliementation des libellés des questions
        List<String> libellesQuestionList = new ArrayList<>();
        for (Question question : Bouchonnage.setDemoSurvey().getQuestions()) {
            libellesQuestionList.add(question.getTextQuestion());
        }

        //Show results
        initResultats(libellesQuestionList, resultsList);
    }

    private class MockedResults {
        private Integer[][] listeResultats;
        private String[] listeQuestions;

        public Integer[][] getListeResultats() {
            return listeResultats;
        }

        public String[] getListeQuestions() {
            return listeQuestions;
        }

        public MockedResults invoke() {
            Integer[] listeResultatsQ1 = new Integer[]{1, 400, 30};
            Integer[] listeResultatsQ2 = new Integer[]{40, 30, 20};
            Integer[] listeResultatsQ3 = new Integer[]{10, 10, 10};

            listeResultats = new Integer[][]{listeResultatsQ1, listeResultatsQ2, listeResultatsQ3};
            Survey survey = Bouchonnage.setDemoSurvey();
            listeQuestions = new String[]{survey.getQuestion(0).getTextQuestion(), survey.getQuestion(1).getTextQuestion(), survey.getQuestion(2).getTextQuestion()};
            return this;
        }
    }
}
