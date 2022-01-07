package ua.kpi.architecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.architecture.domain.Student;
import ua.kpi.architecture.service.FacultyService;
import ua.kpi.architecture.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final FacultyService facultyService;

    public StudentController(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public String displayPerformance(@PathVariable("id") Integer id,
                                     @RequestParam(required = false) String first,
                                     @RequestParam(required = false) String second,
                                     Model model) {
        if (first == null && second == null)
            return "redirect:/students/{id}?first=on&second=on";
        model.addAttribute("student", studentService.findStudentById(id));
        model.addAttribute("marks", studentService.getStudentsMarks(id, first, second));
        return "performance";
    }

    @PostMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newStudent(@RequestParam(required = false) String faculty,
                             @RequestParam(required = false) String specialty, Model model) {
        if (faculty != null) {
            model.addAttribute("specialties", facultyService.findFacultySpecialties(faculty));
            model.addAttribute("groups", facultyService.findFacultyGroups(faculty, specialty));
        }
        model.addAttribute("student", new Student());
        model.addAttribute("faculties", facultyService.findAll());
        model.addAttribute("title", "Add new student");
        model.addAttribute("action", "new");
        return "newStudent";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudentData(@PathVariable("id") Integer id,
                                  @RequestParam(required = false) String faculty,
                                  @RequestParam(required = false) String specialty, Model model) {
        Student student = studentService.findStudentById(id);
        if (faculty == null)
            return "redirect:/students/edit/{id}?faculty=" + student.getGroup().getFaculty().getAbbreviation() +
                    "&specialty=" + student.getGroup().getSpecialty().getId();
        else {
            model.addAttribute("specialties", facultyService.findFacultySpecialties(faculty));
            model.addAttribute("groups", facultyService.findFacultyGroups(faculty, specialty));
        }
        model.addAttribute("student", student);
        model.addAttribute("faculties", facultyService.findAll());
        model.addAttribute("title", "Update information");
        model.addAttribute("action", "edit/" + student.getId());
        return "newStudent";
    }

    @GetMapping("/report")
    public String getReport(@RequestParam(required = false) String list, Model model) {
        if (list == null)
            return "redirect:/students/report?list=Honor+Roll";
        if (list.equals("F-students")) {
            model.addAttribute("students", studentService.getHonorRoll());
            model.addAttribute("title", "F-students List");
        } else {
            model.addAttribute("students", studentService.getFStudentsList());
            model.addAttribute("title", "Honor Roll");
        }
        return "report";
    }
}
