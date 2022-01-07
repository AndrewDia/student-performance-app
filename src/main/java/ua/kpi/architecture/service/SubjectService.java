package ua.kpi.architecture.service;

import org.springframework.stereotype.Service;
import ua.kpi.architecture.repository.SubjectRepository;

import java.util.Map;
import java.util.TreeMap;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Map<Object, Object> getEvaluationMap(Integer semester, Integer specialty, String faculty) {
        Map<Object, Object> subjects = new TreeMap<>();
        if (semester != null)
            subjectRepository.getSemesterAnalysis(semester).forEach(o -> subjects.put(o[0], o[1]));
        else if (specialty != null)
            subjectRepository.getSpecialtyAnalysis(specialty).forEach(o -> subjects.put(o[0], o[1]));
        else if (faculty != null)
            subjectRepository.getFacultyAnalysis(faculty).forEach(o -> subjects.put(o[0], o[1]));
        return subjects;
    }
}
