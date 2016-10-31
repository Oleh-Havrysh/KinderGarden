package hakito.kindergarden.model;

/**
 * Created by Oleg on 31.10.2016.
 */

public class Parent {
    private String name;
    private String address;

    public Parent() {
    }

    public Parent(String name, String address) {
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
