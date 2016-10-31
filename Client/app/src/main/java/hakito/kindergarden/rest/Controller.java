package hakito.kindergarden.rest;

import java.util.List;

import hakito.kindergarden.model.Child;
import hakito.kindergarden.model.Parent;

/**
 * Created by Oleg on 16.10.2016.
 */

public interface Controller {
    List<Child> getChildrens();
    Child getChild(long id);

    Parent getParent(int parentId);
}
