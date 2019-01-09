package ua.nure.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column
    private Date date;
    @Column
    private String comment;
    @Column
    private int behaviour;
    @Column
    private int sleeping;
    @Column
    private int eating;
    @Column
    private int activity;
    @ManyToOne(optional = false)
    @JoinColumn(name = "child_id")
    private Child child;

    public Mark() {
    }

    public Mark(Date date, String comment, int behaviour, int sleeping, int eating, Child child) {
        this.date = date;
        this.comment = comment;
        this.behaviour = behaviour;
        this.sleeping = sleeping;
        this.eating = eating;
        this.child = child;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(int behaviour) {
        this.behaviour = behaviour;
    }

    public int getSleeping() {
        return sleeping;
    }

    public void setSleeping(int sleeping) {
        this.sleeping = sleeping;
    }

    public int getEating() {
        return eating;
    }

    public void setEating(int eating) {
        this.eating = eating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }
}
