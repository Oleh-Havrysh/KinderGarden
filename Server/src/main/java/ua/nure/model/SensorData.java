package ua.nure.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Oleg on 06.12.2016.
 */
@Entity
@Table(name = "sensor_data")
public class SensorData {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
    @Column
    private int activity;

    public SensorData(Child child, int activity) {
        this.child = child;
        this.activity = activity;
    }

    public SensorData() {
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
