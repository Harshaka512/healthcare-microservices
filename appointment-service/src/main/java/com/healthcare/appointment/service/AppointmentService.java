package com.healthcare.appointment.service;

import com.healthcare.appointment.exception.AppointmentNotFoundException;
import com.healthcare.appointment.model.Appointment;
import com.healthcare.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @Transactional
    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment update(Long id, Appointment patch) {
        Appointment existing = getById(id);
        if (patch.getUserId() != null) {
            existing.setUserId(patch.getUserId());
        }
        if (patch.getDoctorId() != null) {
            existing.setDoctorId(patch.getDoctorId());
        }
        if (patch.getDate() != null) {
            existing.setDate(patch.getDate());
        }
        if (patch.getStatus() != null) {
            existing.setStatus(patch.getStatus());
        }
        return appointmentRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException(id);
        }
        appointmentRepository.deleteById(id);
    }
}
