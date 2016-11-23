package ua.nure.easygo.model;

import java.util.Calendar;

/**
 * Created by Oleg on 29.10.2016.
 */
public class Child {
    private String name;
    private Calendar birthDate;

    public Child(String name, Calendar birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Child() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }
}
