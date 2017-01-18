package nantes_sqli.rhchain;

/**
 * Created by alb on 22/11/16.
 *
 * Class recording the value of the answer's chosen, will be store in BC
 *
 * parameters: questionId (String) Identifier of question (question.id) value (int) The value of the
 * chosen answer (answer.value)
 */

public class
Vote {

    String questionId;
    int value;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
