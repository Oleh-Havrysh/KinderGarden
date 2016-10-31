package hakito.kindergarden.model;

/**
 * Created by Oleg on 31.10.2016.
 */

public class User {
    public enum Role {
        ADMIN, TEACHER, PARENT
    }

    private int userId;
    private Role role;
    private String name;

    public User(int userId, Role role, String name) {
        this.userId = userId;
        this.role = role;
        this.name = name;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
