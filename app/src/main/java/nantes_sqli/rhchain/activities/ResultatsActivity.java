package nantes_sqli.rhchain.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sqli.blockchain.android_geth.EthereumService;

import java.util.ArrayList;
import java.util.List;

import ethereumjava.EthereumJava;
import ethereumjava.module.objects.Transaction;
import nantes_sqli.rhchain.R;
import nantes_sqli.rhchain.RhchainApplication;
import nantes_sqli.rhchain.blockchain.GethManager;
import nantes_sqli.rhchain.data.QuestionResultat;
import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.utils.Bouchonnage;
import nantes_sqli.rhchain.utils.QuestionResultatAdapter;
import rx.Observable;

public class ResultatsActivity extends AppCompatActivity implements  EthereumService.EthereumServiceInterface
{
    public static final int LARGEUR_MINIMALE = 50;

    private ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);

        RhchainApplication application = (RhchainApplication) getApplication();
        application.registerGethReady(this);

        GethManager gethManager =application.gethManager;
        EthereumJava eth = gethManager.getEthereumJava();
        List<String> comptes = eth.personal.listAccounts();


        //MOck results
        GetmockedResults getmockedResults = new GetmockedResults().invoke();
        String[] listeQuestions = getmockedResults.getListeQuestions();
        Integer[][] listeResultats = getmockedResults.getListeResultats();

        //Show results
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

    @Override
    public void onEthereumServiceReady() {


    }

    private class GetmockedResults {
        private Integer[][] listeResultats;
        private String[] listeQuestions;

        public Integer[][] getListeResultats() {
            return listeResultats;
        }

        public String[] getListeQuestions() {
            return listeQuestions;
        }

        public GetmockedResults invoke() {
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
