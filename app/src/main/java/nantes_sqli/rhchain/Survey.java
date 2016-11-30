package nantes_sqli.rhchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alb on 15/11/16.
 * Manage survey
 *
 * Same survey could be replay at different time (format: Date), use timeStamp to differenciate.
 * Survey belong to one user (format: User) -> become the admin
 * Could display has many question as necessary
 */

public class Survey implements Serializable{
    String id;
    String label;
    User userOwner;
    Date dateStart;
    Date dateFinish;
    Boolean isCompleted = Boolean.FALSE;
    ArrayList<Question> questions;


    public Survey(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Survey() {
        this.questions = new ArrayList<Question>();
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean bool) {
        isCompleted = bool;
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

    public List<Question> getQuestions(){
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }

    public Question getQuestion(int i) {
        return questions.get(i);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
