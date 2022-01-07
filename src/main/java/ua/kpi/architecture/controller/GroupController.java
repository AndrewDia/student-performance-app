package ua.kpi.architecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kpi.architecture.domain.Group;
import ua.kpi.architecture.service.GroupService;
import ua.kpi.architecture.service.StudentService;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final StudentService studentService;

    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("/{name}")
    public String getGroup(@PathVariable("name") String name, Model model) {
        Group group = groupService.findGroupByName(name);
        model.addAttribute("group", group);
        model.addAttribute("students", studentService.findByGroup(group));
        return "groups";
    }
}
