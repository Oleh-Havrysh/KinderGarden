package hakito.kindergarden.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Oleg on 16.10.2016.
 */

public class Child {
    private String name;
    private Calendar birthDate;
    private String notices;
    private int parentId;

    public Child() {
    }

    public Child(String name, Calendar birthDate, String notices, int parentId) {
        this.name = name;
        this.birthDate = birthDate;
        this.notices = notices;
        this.parentId = parentId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
