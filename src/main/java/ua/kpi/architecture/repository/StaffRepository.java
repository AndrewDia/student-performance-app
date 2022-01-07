package ua.kpi.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.architecture.domain.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
