package nantes_sqli.rhchain;

import java.io.Serializable;

/**
 * @author Created by alb on 15/11/16.
 * @version 1.0 Class to manage available answer.
 *
 *          An answer is defined by : Identifier of answer (String) description (String) (will be
 *          pass in the layout to be display) value (int): the value that will be store in the BC
 *          nameImage(String) : the name af the drawable to be display in layout
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
