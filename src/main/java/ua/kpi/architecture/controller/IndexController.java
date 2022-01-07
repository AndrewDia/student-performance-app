package ua.kpi.architecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.architecture.service.StudentService;

@Controller
@RequestMapping("/")
public class IndexController {
    private final StudentService studentService;

    public IndexController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"", "/students"})
    public String getStudentsList(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "index";
    }

    @PostMapping
    public String findStudent(@RequestParam String search, Model model) {
        model.addAttribute("students", studentService.findByKey(search));
        model.addAttribute("afterSearch", true);
        return "index";
    }
}
