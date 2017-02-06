package nantes_sqli.rhchain.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Created by alb on 15/11/16.
 * @version 1.0 Class to manage available answer.
 *
 *          An answer is defined by : Identifier of answer (String) description (String) (will be
 *          pass in the layout to be display) value (int): the value that will be store in the BC
 *          nameImage(String) : the name af the drawable to be display in layout
 */

public class Answer implements Parcelable {

    private String id;
    private String description;
    private int value;

    private String nameImage;
    private String nameImageSelected;

    // Constructeurs
    public Answer(String id, String description, int value, int nameImage,int nameImageSelected) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.nameImage = String.valueOf(nameImage);
        this.nameImageSelected = String.valueOf(nameImageSelected);
    }

    protected Answer(Parcel in) {
        this.id = in.readString();
        this.description = in.readString();
        this.value = in.readInt();
        this.nameImage = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeInt(this.value);
        dest.writeString(this.nameImage);
    }



    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
