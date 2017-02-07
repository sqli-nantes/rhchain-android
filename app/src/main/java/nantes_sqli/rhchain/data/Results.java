package nantes_sqli.rhchain.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alb on 22/11/16. Votes for a specific survey
 *
 * class to import data store in BC Not working at this time Parameters: User's list who had
 * answered Vote, containing array per questions id's question and answer's value
 */

public class Results implements Parcelable {
    /**
     * the user who have answered
     */
    private User user;
    /**
     * list of votes providing the identifiers of questions and answers
     */
    private Answer[] reponseSelectionnees;
    /**
     * survey from which the results are display
     */
    private Survey survey;

    public Results(Survey survey, User user) {
        this.reponseSelectionnees = new Answer[survey.getQuestions().size()];
        this.survey = survey;
        this.user = user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.reponseSelectionnees, 0);
        dest.writeSerializable(this.user);
        dest.writeParcelable(this.survey, flags);

    }
    protected Results(Parcel in) {
        this.user                   = (User) in.readSerializable();
        this.reponseSelectionnees   = in.createTypedArray(Answer.CREATOR);
        this.survey                 = in.readParcelable(Survey.class.getClassLoader());

    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer[]  getReponseSelectionnees() {
        return reponseSelectionnees;
    }

    public void setReponseSelectionnees(Answer[]  reponseSelectionnees) {
        this.reponseSelectionnees = reponseSelectionnees;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
