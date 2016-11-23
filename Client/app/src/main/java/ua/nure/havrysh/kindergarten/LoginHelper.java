package ua.nure.havrysh.kindergarten;

import android.content.Context;
import android.content.SharedPreferences;

import ua.nure.havrysh.kindergarten.model.User;

/**
 * Created by Oleg on 31.10.2016.
 */

public enum LoginHelper {
    INSTANCE;

    private static final String PREFS = "login_prefs", USER_NAME = "user_name", PASSWORD = "password!";

    private User user;

    LoginHelper() {

    }


    public User getUser() {
        return user;
    }

    //admin@nure.ua
    public void setUser(Context contxt, User user) {
        this.user = user;
        SharedPreferences preferences = contxt.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_NAME, user.getName());
        editor.apply();
        editor.commit();

    }

    public boolean loggedIn(Context context) {
        if (user == null) {
            if ((context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).contains(USER_NAME))) {
                user = new User(1, User.Role.ADMIN, context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(USER_NAME, ""));
                return true;
            }
        }
        return false;

    }

}
