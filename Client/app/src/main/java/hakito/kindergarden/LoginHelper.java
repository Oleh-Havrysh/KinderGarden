package hakito.kindergarden;

import hakito.kindergarden.model.User;

/**
 * Created by Oleg on 31.10.2016.
 */

public enum LoginHelper {
    INSTANCE;

    private User user;

    LoginHelper() {

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean loggedIn() {
        return user != null;
    }

}
