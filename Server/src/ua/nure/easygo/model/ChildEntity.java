package ua.nure.easygo.model;

import javax.persistence.*;

/**
 * Created by Oleg on 12.11.2016.
 */
@Entity
@Table(name = "child", schema = "kindergarten_schema", catalog = "")
public class ChildEntity {
    private int childId;
    private String childName;

    @Id
    @Column(name = "child_id")
    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    @Basic
    @Column(name = "child_name")
    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChildEntity that = (ChildEntity) o;

        if (childId != that.childId) return false;
        if (childName != null ? !childName.equals(that.childName) : that.childName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = childId;
        result = 31 * result + (childName != null ? childName.hashCode() : 0);
        return result;
    }
}
