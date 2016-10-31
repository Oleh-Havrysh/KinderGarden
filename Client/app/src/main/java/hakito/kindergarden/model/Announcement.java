package hakito.kindergarden.model;

import java.util.Calendar;

/**
 * Created by Oleg on 31.10.2016.
 */

public class Announcement {
    private String title, description;
    private Calendar date;

    public Announcement() {
    }

    public Announcement(String title, String description, Calendar date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
