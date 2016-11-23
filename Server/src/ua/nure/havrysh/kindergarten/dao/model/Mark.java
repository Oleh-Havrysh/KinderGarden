package ua.nure.havrysh.kindergarten.dao.model;

import java.sql.Date;

public class Mark {
    private long mark_id;
    private java.sql.Date date;
    private String comment;
    private long behaviour;
    private long sleeping;
    private long eating;
    private long child_id;

    public Mark() {
    }

    public Mark(long mark_id, Date date, String comment, long behaviour, long sleeping, long eating, long child_id) {
        this.mark_id = mark_id;
        this.date = date;
        this.comment = comment;
        this.behaviour = behaviour;
        this.sleeping = sleeping;
        this.eating = eating;
        this.child_id = child_id;
    }

    public long getMark_id() {
        return mark_id;
    }

    public void setMark_id(long mark_id) {
        this.mark_id = mark_id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(long behaviour) {
        this.behaviour = behaviour;
    }

    public long getSleeping() {
        return sleeping;
    }

    public void setSleeping(long sleeping) {
        this.sleeping = sleeping;
    }

    public long getEating() {
        return eating;
    }

    public void setEating(long eating) {
        this.eating = eating;
    }

    public long getChild_id() {
        return child_id;
    }

    public void setChild_id(long child_id) {
        this.child_id = child_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Mark ");
        sb.append("mark_id=").append(mark_id);
        sb.append(", date=").append(date);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", behaviour=").append(behaviour);
        sb.append(", sleeping=").append(sleeping);
        sb.append(", eating=").append(eating);
        sb.append(", child_id=").append(child_id);
        sb.append('\n');
        return sb.toString();
    }
}
