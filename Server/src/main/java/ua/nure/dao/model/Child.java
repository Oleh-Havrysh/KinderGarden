package ua.nure.dao.model;

import java.sql.Date;

public class Child {
    private long child_id;
    private String name;
    private String surname;
    private Date birth_date;
    private long group_id;
    private long parent_id;
    private String notice;

    public Child(long child_id, String name, String surname, Date birth_date, long group_id, long parent_id, String notice) {
        this.child_id = child_id;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.group_id = group_id;
        this.parent_id = parent_id;
        this.notice = notice;
    }

    public Child() {
    }

    public long getChild_id() {
        return child_id;
    }

    public void setChild_id(long child_id) {
        this.child_id = child_id;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Child ");
        sb.append("child_id=").append(child_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", birth_date=").append(birth_date);
        sb.append(", group_id=").append(group_id);
        sb.append(", parent_id=").append(parent_id);
        sb.append(", notice='").append(notice).append('\'');
        sb.append("\n");
        return sb.toString();
    }
}
