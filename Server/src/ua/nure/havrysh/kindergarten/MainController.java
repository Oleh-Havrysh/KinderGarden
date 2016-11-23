package ua.nure.havrysh.kindergarten;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.havrysh.kindergarten.dao.KindergartenDAO;
import ua.nure.havrysh.kindergarten.dao.model.Announcement;
import ua.nure.havrysh.kindergarten.dao.model.Child;
import ua.nure.havrysh.kindergarten.dao.model.Group;
import ua.nure.havrysh.kindergarten.dao.model.Human;

import java.util.List;

/**
 * Created by Oleg on 22.11.2016.
 */
@RestController
public class MainController {

    @RequestMapping("/humans/{id}")
    public Human getHuman(@PathVariable("id") long id) {
        return KindergartenDAO.getInstance().getHuman(id);
    }

    @RequestMapping("/announcements")
    public List<Announcement> getAnnouncements() {
        return KindergartenDAO.getInstance().getAnnouncements();
    }

    @RequestMapping("/announcements/{id}")
    public Announcement getAnnouncement(@PathVariable("id") long id) {
        return KindergartenDAO.getInstance().getAnnouncement(id);
    }

    @RequestMapping("/groups")
    public List<Group> getGroups() {
        return KindergartenDAO.getInstance().getGroups();
    }

    @RequestMapping("/groups/{id}")
    public Group getGroup(@PathVariable("id") long id) {
        return KindergartenDAO.getInstance().getGroup(id);
    }

    @RequestMapping("/groups/{groupId}/children")
    public List<Child> getChildrenOfGroup(@PathVariable("groupId") long groupId) {
        return KindergartenDAO.getInstance().getChildren(groupId);
    }

    @RequestMapping("/children/{id}")
    public Child getChild(@PathVariable("id") long id) {
        return KindergartenDAO.getInstance().getChild(id);
    }

}
