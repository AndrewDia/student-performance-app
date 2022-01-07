package ua.kpi.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.architecture.domain.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Faculty findFacultyByAbbreviation(String abbreviation);
}
