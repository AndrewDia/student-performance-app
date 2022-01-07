package ua.kpi.architecture.service;

import org.springframework.stereotype.Service;
import ua.kpi.architecture.domain.Specialty;
import ua.kpi.architecture.repository.SpecialtyRepository;

import java.util.List;

@Service
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<Specialty> findAllByOrderById() {
        return specialtyRepository.findAllByOrderById();
    }
}
