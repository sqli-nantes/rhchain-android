package nantes_sqli.rhchain;

import android.support.annotation.NonNull;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import nantes_sqli.rhchain.data.Results;
import nantes_sqli.rhchain.utils.Bouchonnage;

import static org.junit.Assert.*;

/**
 * Created by mcame on 07/02/17.
 */
public class SurveysFragmentTest {
    @Test
    public void isNewSurveyNotCompleted() throws Exception {
        SurveysFragment surveysFragment = newSurveysFragment();

        boolean zeroReponse = surveysFragment.isSurveyCompleted();
        Assert.assertFalse(zeroReponse);
    }

    @Test
    public void uneReponseSurTrois() throws Exception {
        SurveysFragment surveysFragment = newSurveysFragment();
        // premiere question avec valeur 0 de reponse
        surveysFragment.setVote(0,0);

        boolean uneReponse = surveysFragment.isSurveyCompleted();
        Assert.assertFalse(uneReponse);
    }
    @Test
    public void DeuxReponsesSurTrois() throws Exception {
        SurveysFragment surveysFragment = newSurveysFragment();
        // premiere question avec valeur 0 de reponse
        surveysFragment.setVote(0,0);
        surveysFragment.setVote(1,0);

        boolean uneReponse = surveysFragment.isSurveyCompleted();
        Assert.assertFalse(uneReponse);
    }

    @Test
    public void secondeReponseSurTrois() throws Exception {
        SurveysFragment surveysFragment = newSurveysFragment();
        // premiere question avec valeur 0 de reponse
        surveysFragment.setVote(1,0);

        boolean uneReponse = surveysFragment.isSurveyCompleted();
        Assert.assertFalse(uneReponse);
    }


    @Test
    public void troisReponseCompleted() throws Exception {
        SurveysFragment surveysFragment = newSurveysFragment();
        for (int i = 0; i < surveysFragment.getSurvey().getQuestions().size(); i++) {
            surveysFragment.setVote(i,0);
        }
        boolean iscomplet = surveysFragment.isSurveyCompleted();
        Assert.assertTrue(iscomplet);
    }

    private SurveysFragment newSurveysFragment() {
        SurveysFragment surveysFragment = new SurveysFragment();
        surveysFragment.setSurvey(Bouchonnage.setDemoSurvey());
        surveysFragment.setResults(new Results(Bouchonnage.setDemoSurvey(), null));
        return surveysFragment;
    }

}
