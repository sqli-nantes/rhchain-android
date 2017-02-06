package nantes_sqli.rhchain.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alb on 15/11/16.
 *
 * Class to manage question display.
 *
 * parameters: Identifier of question (String) textQuestion text to display (String) answers list of
 * available answers (ArrayList<Answer>)
 */

public class Question implements Parcelable {
    String id;
    /**
     * Text to be displayed as question
     */
    String textQuestion;
    /**
     * List of available Answer for the present question
     */
    List<Answer> answers;

    public Question(List<Answer> answers) {
        this.answers = answers;
    }

    public Question() {
        this.answers = new ArrayList<Answer>();

    }

    public Question(String id, String textQuestion, List<Answer> answers) {
        this.answers = answers;
        this.textQuestion= textQuestion;
        this.id = id;
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

    /**
     * compute the number of answers display in the survey
     * @return nbAnswer (int)
     */
    public int nbAnswer() {
        int nbAnswer = answers.size();
        return nbAnswer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.textQuestion);
        dest.writeList(this.answers);
    }

    protected Question(Parcel in) {
        this.id = in.readString();
        this.textQuestion = in.readString();
        this.answers = new ArrayList<Answer>();
        in.readList(this.answers, Answer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
