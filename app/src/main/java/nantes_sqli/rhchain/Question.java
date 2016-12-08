package nantes_sqli.rhchain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alb on 15/11/16.
 *
 * Class to manage question display.
 *
 * parameters: Identifier of question (String) textQuestion text to display (String) answers list of
 * available answers (ArrayList<Answer>)
 */

public class Question implements Serializable {
    String id;
    /**
     * Text to be displayed as question
     */
    String textQuestion;
    /**
     * List of available Answer for the present question
     */
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

    /**
     * Add an answer to the question's object
     *
     * @param answer (Answer)
     */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
