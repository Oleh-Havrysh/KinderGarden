package ua.nure.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    private Human teacher;

    public Group() {
    }

    public Group(String name, Human teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Human getTeacher() {
        return teacher;
    }

    public void setTeacher(Human teacher) {
        this.teacher = teacher;
    }
}
