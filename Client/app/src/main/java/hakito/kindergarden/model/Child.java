package hakito.kindergarden.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Oleg on 16.10.2016.
 */

public class Child {
    private String name, surname;
    private Calendar birthDate;
    private String notices;

    public Child() {
    }

    public Child(String name, String surname, Calendar birthDate, String notices) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.notices = notices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDateString()
    {
        return new SimpleDateFormat("dd.MM.yyyy").format(birthDate.getTime());
    }

    public String getNotices() {
        return notices;
    }

    public void setNotices(String notices) {
        this.notices = notices;
    }

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }
}
