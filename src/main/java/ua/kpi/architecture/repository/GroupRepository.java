package ua.kpi.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.architecture.domain.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findGroupByName(String name);
}
