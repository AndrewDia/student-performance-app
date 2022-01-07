package ua.kpi.architecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kpi.architecture.domain.Faculty;
import ua.kpi.architecture.service.FacultyService;

import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String displayList(Model model) {
        List<Faculty> faculties = facultyService.findAll();
        model.addAttribute("faculties", faculties);
        return "faculties";
    }

    @GetMapping("/{abbreviation}")
    public String displayFaculty(@PathVariable("abbreviation") String abbreviation, Model model) {
        model.addAttribute("faculties", facultyService.findAll());
        model.addAttribute("pathFaculty", facultyService.findFacultyByAbbreviation(abbreviation));
        model.addAttribute("specialties", facultyService.findFacultySpecialties(abbreviation));
        return "faculties";
    }
}
