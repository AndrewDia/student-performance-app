package ua.kpi.architecture.service;

import org.springframework.stereotype.Service;
import ua.kpi.architecture.domain.Faculty;
import ua.kpi.architecture.domain.Group;
import ua.kpi.architecture.domain.Specialty;
import ua.kpi.architecture.repository.FacultyRepository;
import ua.kpi.architecture.repository.SpecialtyRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final SpecialtyRepository specialtyRepository;

    public FacultyService(FacultyRepository facultyRepository, SpecialtyRepository specialtyRepository) {
        this.facultyRepository = facultyRepository;
        this.specialtyRepository = specialtyRepository;
    }
    
    public Faculty findFacultyByAbbreviation(String abbreviation) {
        return facultyRepository.findFacultyByAbbreviation(abbreviation);
    }
    
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public Set<Specialty> findFacultySpecialties(String abbreviation) {
        Faculty faculty = facultyRepository.findFacultyByAbbreviation(abbreviation);
        return faculty.getGroups().stream()
                .map(Group::getSpecialty)
                .collect(Collectors.toSet());
    }

    public Set<Group> findFacultyGroups(String abbreviation, String specialty) {
        Faculty faculty = facultyRepository.findFacultyByAbbreviation(abbreviation);
        if (specialty != null) {
            Set<Group> specialtyGroups = specialtyRepository.getById(Integer.parseInt(specialty)).getGroups();
            specialtyGroups.retainAll(faculty.getGroups());
            return specialtyGroups;
        }
        return faculty.getGroups();
    }
}
