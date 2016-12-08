package nantes_sqli.rhchain;

import java.io.Serializable;

/**
 * Created by alb on 14/11/16. User Object
 *
 * Attention, cette fonction est en mode démo. Toto est l'utilisateur par défaut le password est
 * inscrit dans le compte blockchain
 */

public class User implements Serializable {
    String identity;
    String password = null;
    /**
     * Is the user administrator? Boolean (default = FALSE)
     */
    boolean isAdmin = false;

    public User(String identity) {
        this.identity = identity;
    }


    public User() {
        this.identity = new String();
//        identity = "toto";
    }


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public static User getUserById(String identity) {
        User user = new User();
//        Pour la démo
        user.setIdentity("toto");
        return user;
    }

}

