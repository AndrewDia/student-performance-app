package ua.kpi.architecture.service;

import org.springframework.stereotype.Service;
import ua.kpi.architecture.domain.Group;
import ua.kpi.architecture.domain.Mark;
import ua.kpi.architecture.domain.Student;
import ua.kpi.architecture.repository.GroupRepository;
import ua.kpi.architecture.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Mark> getStudentsMarks(Integer studentId, String firstSemester, String secondSemester) {
        Student student = studentRepository.getById(studentId);
        if (firstSemester != null && secondSemester != null)
            return student.getMarks();
        else if (firstSemester != null)
            return student.getMarks().stream()
                    .filter(x -> x.getSemester() == 1)
                    .collect(Collectors.toList());
        else
            return student.getMarks().stream()
                    .filter(x -> x.getSemester() == 2)
                    .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    public void save(Student student) {
        Group group = groupRepository.findGroupByName(student.getGroup().getName());
        student.setGroup(group);
        studentRepository.save(student);
    }

    public List<Student> getHonorRoll() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getMarks().stream()
                        .anyMatch(mark -> mark.getScore() < 60))
                .collect(Collectors.toList());
    }

    public List<Student> getFStudentsList() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getMarks().stream().findAny().isPresent())
                .filter(student -> student.getMarks().stream()
                        .allMatch(mark -> mark.getScore() >= 95))
                .collect(Collectors.toList());
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Set<Student> findByKey(String key) {
        Set<Student> students = studentRepository.findByNameContaining(key);
        students.addAll(studentRepository.findByEmailStartingWith(key));
        students.addAll(studentRepository.findByPhoneStartingWith(key));
        return students;
    }

    public List<Student> findByGroup(Group group) {
        List<Student> students = new ArrayList<>(group.getStudents());
        students.sort(Comparator.comparing(Student::getName));
        return students;
    }
}
