package ua.nure.dao.model;

import java.sql.Date;

public class Announcement {
    private long announcement_id;
    private String title;
    private String content;
    private Date creation_date;
    private Date expires;

    public Announcement() {
    }

    public Announcement(long announcement_id, String title, String content, Date creation_date, Date expires) {
        this.announcement_id = announcement_id;
        this.title = title;
        this.content = content;
        this.creation_date = creation_date;
        this.expires = expires;
    }

    public long getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(long announcement_id) {
        this.announcement_id = announcement_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Announcement ");
        sb.append("announcement_id=").append(announcement_id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", creation_date=").append(creation_date);
        sb.append(", expires=").append(expires);
        sb.append('\n');
        return sb.toString();
    }
}
