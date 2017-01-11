package nantes_sqli.rhchain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alb on 15/11/16.
 * <p>
 * Manage survey component of a single survey
 * <p>
 * Same survey could be replay at different time (format: Date), use timeStamp to differentiate. Survey belong to one
 * owner (format: User)
 * <p>
 * The survey is set to have 3 questions could be answer by unsatisfied, neutral or satisfied
 * <p>
 * parameters : Identifier of survey (String); Label (String); Starting and ending date (Date);
 * Questions (Question) => specific class to set question's label and available answer
 */

public class Survey implements Parcelable {
    String id;
    String label;
    User userOwner;
    Date dateStart;
    Date dateFinish;
    ArrayList<Question> questions;


    public Survey(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Survey() {
        this.questions = new ArrayList<Question>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Question getQuestion(int i) {
        return questions.get(i);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    /**
     * compute the number of questions display in the survey
     * @return nbQuestion (int)
     */
    public int nbQuestion() {
        int nbQuestion = questions.size();
        return nbQuestion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.label);
        dest.writeSerializable(this.userOwner);
        dest.writeLong(this.dateStart != null ? this.dateStart.getTime() : -1);
        dest.writeLong(this.dateFinish != null ? this.dateFinish.getTime() : -1);
        dest.writeList(this.questions);
    }

    protected Survey(Parcel in) {
        this.id = in.readString();
        this.label = in.readString();
        this.userOwner = (User) in.readSerializable();
        long tmpDateStart = in.readLong();
        this.dateStart = tmpDateStart == -1 ? null : new Date(tmpDateStart);
        long tmpDateFinish = in.readLong();
        this.dateFinish = tmpDateFinish == -1 ? null : new Date(tmpDateFinish);
        this.questions = new ArrayList<Question>();
        in.readList(this.questions, Question.class.getClassLoader());
    }

    public static final Parcelable.Creator<Survey> CREATOR = new Parcelable.Creator<Survey>() {
        @Override
        public Survey createFromParcel(Parcel source) {
            return new Survey(source);
        }

        @Override
        public Survey[] newArray(int size) {
            return new Survey[size];
        }
    };
}
