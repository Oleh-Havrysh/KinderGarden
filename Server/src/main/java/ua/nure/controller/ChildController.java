package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.model.Child;
import ua.nure.model.Group;
import ua.nure.model.Human;
import ua.nure.security.jwt.JwtTokenProvider;
import ua.nure.service.ChildService;
import ua.nure.service.GroupService;
import ua.nure.service.HumanService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ChildController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private HumanService humanService;

    @Autowired
    private ChildService childService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/groups")
    public List<Group> getGroups() {
        return groupService.findAll();
    }

    @GetMapping("/groupsByTeacher/{id}")
    public List<Group> getGroupsByTeacher(@PathVariable("id") String id) {
        Human teacher=humanService.findById(id);
        return groupService.findByTeacher(teacher);
    }

    @GetMapping("/groups/{id}")
    public Group getGroup(@PathVariable("id") String id) {
        return groupService.findById(id);
    }

    @GetMapping(value = "/childrenByGroup/{group}")
    public List<Child> getChildrenByGroup(@PathVariable("group") String groupId) {
        Group group = groupService.findById(groupId);
        return childService.findByGroup(group);
    }

    @GetMapping("/children/{id}")
    public Child getChild(@PathVariable("id") String id) {
        return childService.findById(id);
    }

    @GetMapping(value = "/myChildren")
    public List<Child> getChildrenByParent(HttpServletRequest request) {
        String jwt = jwtTokenProvider.getJwtFromRequest(request);
        String userId = jwtTokenProvider.getUserIdFromJwt(jwt);
        Human parent = humanService.findById(userId);
        return childService.findByParent(parent);
    }
}
