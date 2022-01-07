package ua.kpi.architecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.architecture.service.FacultyService;
import ua.kpi.architecture.service.SpecialtyService;
import ua.kpi.architecture.service.SubjectService;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {
    private final SubjectService subjectService;
    private final FacultyService facultyService;
    private final SpecialtyService specialtyService;

    public AnalysisController(SubjectService subjectService, FacultyService facultyService, SpecialtyService specialtyService) {
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.specialtyService = specialtyService;
    }

    @GetMapping
    public String getAnalysis(@RequestParam(required = false) Integer semester,
                              @RequestParam(required = false) Integer specialty,
                              @RequestParam(required = false) String faculty, Model model) {
        if (semester == null && specialty == null && faculty == null)
            return "redirect:/analysis?semester=1";
        model.addAttribute("subjects", subjectService.getEvaluationMap(semester, specialty, faculty));
        model.addAttribute("faculties", facultyService.findAll());
        model.addAttribute("specialties", specialtyService.findAllByOrderById());
        return "analysis";
    }
}
