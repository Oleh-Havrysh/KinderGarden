package hakito.kindergarden.rest;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import hakito.kindergarden.model.Child;

/**
 * Created by Oleg on 16.10.2016.
 */

public class ControllerImpl implements Controller {
    @Override
    public List<Child> getChildrens() {
        List<Child> children = new LinkedList<>();


        children.add(new Child("Nastya", "Fedorenko", new GregorianCalendar(1997, 1, 11), "Pretty"));
        children.add(new Child("Oleh", "Havrysh", new GregorianCalendar(1997, 9, 16), "f-"));
        children.add(new Child("Irina", "Petrenko", new GregorianCalendar(1997, 4, 27), "kot"));
        return children;
    }

    @Override
    public Child getChild(long id) {
        return getChildrens().get((int)id);
    }
}
