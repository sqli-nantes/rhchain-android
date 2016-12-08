package nantes_sqli.rhchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alb on 15/11/16.
 * <p>
 * Manage survey Composante of a single survey
 * <p>
 * Same survey could be replay at different time (format: Date), use timeStamp to differenciate. Survey belong to one
 * owner (format: User) -> the admin could display has many question as necessary
 *
 * parameters : Identifier of survey (String); Label (String); Starting and ending date (Date);
 * Questions (Question) => specific class to set question's label and available answer
 */

public class Survey implements Serializable {
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
}
