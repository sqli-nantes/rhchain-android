package nantes_sqli.rhchain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alb on 15/11/16.
 */

public class Question {
    String id;
    String textQuestion;
    List<Answer> answers;

    public Question(List<Answer> answers) {
        this.answers = answers;
    }

    public Question() {
        this.answers =  new ArrayList<Answer>();

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

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public Answer getAnswer(int i) {
        return answers.get(i);

    }

    public void setAnswers(List<Answer> answers){
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
