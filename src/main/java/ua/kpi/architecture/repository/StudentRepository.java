package ua.kpi.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.architecture.domain.Student;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Set<Student> findByNameContaining(String name);
    Set<Student> findByEmailStartingWith(String email);
    Set<Student> findByPhoneStartingWith(String phone);
}
