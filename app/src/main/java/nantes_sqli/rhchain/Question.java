package nantes_sqli.rhchain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alb on 15/11/16.
 */

public class Question implements Serializable {
    String id;
    String textQuestion;
    ArrayList<Answer> answers;

    public Question(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Question() {
        this.answers = new ArrayList<Answer>();

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Answer getAnswer(int i) {
        return answers.get(i);

    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
