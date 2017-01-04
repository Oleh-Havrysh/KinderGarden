package ua.nure;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.dao.*;
import ua.nure.dao.model.*;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oleg on 22.11.2016.
 */
@MultipartConfig(maxFileSize = 10000000)
@CrossOrigin
@RestController
public class MainController {

    //human

    @RequestMapping("/humans/{id}")
    public Human getHuman(@PathVariable("id") long id) {
        return HumanDAO.get().getHuman(id);
    }


    //announcement

    @RequestMapping("/announcements")
    public List<Announcement> getAnnouncements() {
        return AnnouncementDAO.get().getAnnouncements();
    }

    @RequestMapping("/announcements/{id}")
    public Announcement getAnnouncement(@PathVariable("id") long id) {
        return AnnouncementDAO.get().getAnnouncement(id);
    }


    //group

    @RequestMapping("/groups")
    public List<Group> getGroups() {
        return GroupDAO.get().getGroups();
    }

    @RequestMapping("/groups/{id}")
    public Group getGroup(@PathVariable("id") long id) {
        return GroupDAO.get().getGroup(id);
    }

    @RequestMapping("/groups/{groupId}/children")
    public List<Child> getChildrenOfGroup(@PathVariable("groupId") long groupId) {
        return ChildDAO.get().getChildren(groupId);
    }


    //Child

    @RequestMapping("/children/{id}")
    public Child getChild(@PathVariable("id") long id) {
        return ChildDAO.get().getChild(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sensors")
    public Boolean saveSensorData(@RequestBody SensorData data) {
        return SensorDataDAO.get().save(data);

    }


    //mark

    @RequestMapping("/marks/{id}")
    public Mark getMark(@PathVariable("id") long markId){
        return MarkDAO.get().getMark(markId);
    }

    @RequestMapping("/children/{id}/marks")
    public List<Mark> getMarks(@PathVariable("id") long childId) {
        return MarkDAO.get().getMarks(childId);
    }

    @RequestMapping(value = "/marks", method = RequestMethod.POST)
    public Boolean postMark(@RequestBody Mark mark) {
        MarkDAO.get().insert(mark);
        return true;
    }

    @RequestMapping(value = "/image/{path}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("path") String path) throws IOException {
        return ImageHelper.load(path);
    }

    @RequestMapping(value = "/image/{path}", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("img") MultipartFile img, @PathVariable("path") String path) throws IOException {
        ImageHelper.save(path, img.getBytes());
        return "OK";
    }
}
