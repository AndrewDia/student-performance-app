package ua.kpi.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.kpi.architecture.domain.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT name, AVG(mark) FROM subject " +
            "JOIN marks ON subject.id = marks.subject_id " +
            "WHERE semester = ? " +
            "GROUP BY id ORDER BY AVG(mark) DESC", nativeQuery = true)
    List<Object[]> getSemesterAnalysis(Integer semester);

    @Query(value = "SELECT subject.name, AVG(mark) FROM subject " +
            "JOIN marks ON subject.id = marks.subject_id " +
            "JOIN student on student.id = marks.student_id " +
            "JOIN `group` on `group`.id = student.group_id " +
            "WHERE specialty_id = ? " +
            "GROUP BY subject.id ORDER BY AVG(mark) DESC;", nativeQuery = true)
    List<Object[]> getSpecialtyAnalysis(Integer specialty);

    @Query(value = "SELECT subject.name, AVG(mark) FROM subject " +
            "JOIN marks ON subject.id = marks.subject_id " +
            "JOIN student on student.id = marks.student_id " +
            "JOIN `group` on `group`.id = student.group_id " +
            "JOIN faculty on faculty.id = `group`.faculty_id " +
            "WHERE faculty.abbreviation = ? " +
            "GROUP BY subject.id ORDER BY AVG(mark) DESC", nativeQuery = true)
    List<Object[]> getFacultyAnalysis(String facultyAbbreviation);
}
