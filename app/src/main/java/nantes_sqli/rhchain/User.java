package nantes_sqli.rhchain;

/**
 * Created by alb on 14/11/16.
 * User Object
 */

public class User {
    String identity;
    String password;
    Boolean isAdmin = Boolean.FALSE;

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

}
