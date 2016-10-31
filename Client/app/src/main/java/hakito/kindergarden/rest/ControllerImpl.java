package hakito.kindergarden.rest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import hakito.kindergarden.model.Announcement;
import hakito.kindergarden.model.Child;
import hakito.kindergarden.model.Parent;

/**
 * Created by Oleg on 16.10.2016.
 */

public class ControllerImpl implements Controller {
    static List<Child> children = new LinkedList<>();
    static List<Parent> parents = new LinkedList<>();
    static List<Announcement> announcements = new LinkedList<>();

    static {
        announcements.add(new Announcement("Stub", "Simple annonunfv", Calendar.getInstance()));
        announcements.add(new Announcement("Ohol", "description", Calendar.getInstance()));
        announcements.add(new Announcement("erg", "buity", Calendar.getInstance()));
        announcements.add(new Announcement("lorem", "weird", Calendar.getInstance()));
    }

    static {
        parents.add(new Parent("Lin", "alt"));
        parents.add(new Parent("Sv", "sem"));
        parents.add(new Parent("Lyd", "kote"));
    }

    static {

        children.add(new Child("Nastya", new GregorianCalendar(1997, 1, 11), "Pretty", 0));
        children.add(new Child("Oleh", new GregorianCalendar(1997, 9, 16), "f-", 1));
        children.add(new Child("Irina", new GregorianCalendar(1997, 4, 27), "kot", 2));
    }

    @Override
    public List<Child> getChildrens() {


        return children;
    }

    @Override
    public Child getChild(long id) {
        return getChildrens().get((int) id);
    }

    @Override
    public Parent getParent(int parentId) {
        return parents.get(parentId);
    }


    public List<Announcement> getAnnouncements() {
        return announcements;
    }
}
