package nantes_sqli.rhchain;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import nantes_sqli.rhchain.data.Answer;
import nantes_sqli.rhchain.data.Survey;
import nantes_sqli.rhchain.utils.AnswersFactory;
import nantes_sqli.rhchain.utils.AnswersType;
import nantes_sqli.rhchain.utils.Bouchonnage;

public class AnswersFactoryTest {

    @Test
    public void threeAnswersBuild() {
        List<Answer> answerList = AnswersFactory.createAnswers(AnswersType.THREE_ANSWERS);
        Assert.assertEquals(3, answerList.size());
        assertAnswser(answerList.get(0), "insatisfait",     R.drawable.ic_sentiment_dissatisfied_black_24dp);
        assertAnswser(answerList.get(1), "satisfait",       R.drawable.ic_sentiment_neutral_black_24dp);
        assertAnswser(answerList.get(2), "très satisfait",  R.drawable.ic_sentiment_satisfied_black_48dp);
    }

    private void assertAnswser(Answer answer ,String description, int nameImage){
        Assert.assertEquals(String.valueOf(nameImage), answer.getNameImage());
        Assert.assertEquals(description, answer.getDescription());
    }

    @Test
    public void testGenerationSurvey(){

        //Generation du survey
        Survey survey = Bouchonnage.setDemoSurvey();

        // Verification
        Assert.assertNotNull(survey);
        Assert.assertEquals(3, survey.getQuestions().size());
        for(int i=0;i<3;i++){
            Assert.assertNotNull(survey.getQuestion(i));
            Assert.assertEquals(toString().valueOf(i),survey.getQuestion(i).getId());
        }

    }





}
