package nantes_sqli.rhchain;

import java.io.Serializable;

/**
 * Created by alb on 15/11/16.
 */

public class Answer implements Serializable {
    String id;
    String description;
    int value;
    String nameImage;

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
}
