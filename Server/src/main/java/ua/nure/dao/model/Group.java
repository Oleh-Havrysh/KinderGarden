package ua.nure.dao.model;

public class Group {
    private long group_id;
    private String name;
    private long teacher_id;

    public Group() {
    }

    public Group(long group_id, String name, long teacher_id) {
        this.group_id = group_id;
        this.name = name;
        this.teacher_id = teacher_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(long teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group ");
        sb.append("group_id=").append(group_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", teacher_id=").append(teacher_id);
        sb.append('\n');
        return sb.toString();
    }
}
