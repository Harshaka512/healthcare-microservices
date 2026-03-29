package com.healthcare.doctor.service;

import com.healthcare.doctor.exception.DoctorNotFoundException;
import com.healthcare.doctor.model.Doctor;
import com.healthcare.doctor.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor getById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @Transactional
    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor update(Long id, Doctor patch) {
        Doctor existing = getById(id);
        if (patch.getName() != null) {
            existing.setName(patch.getName());
        }
        if (patch.getSpecialization() != null) {
            existing.setSpecialization(patch.getSpecialization());
        }
        if (patch.getAvailability() != null) {
            existing.setAvailability(patch.getAvailability());
        }
        return doctorRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException(id);
        }
        doctorRepository.deleteById(id);
    }
}
