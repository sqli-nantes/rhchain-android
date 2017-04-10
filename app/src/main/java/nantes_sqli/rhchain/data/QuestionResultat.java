package nantes_sqli.rhchain.data;

import java.util.List;

/**
 * Created by mcame on 31/03/17.
 */

public class QuestionResultat {
    private String question;
    private Integer[] resultats;

    public QuestionResultat() {
    }

    public QuestionResultat(String question, Integer[] resultats) {
        this.question = question;
        this.resultats = resultats;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer[] getResultats() {
        return resultats;
    }

    public void setResultats(Integer[] resultats) {
        this.resultats = resultats;
    }
}
