package com.healthcare.doctor.controller;

import com.healthcare.doctor.model.Doctor;
import com.healthcare.doctor.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@Tag(name = "Doctors", description = "Doctor profiles and availability")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Operation(summary = "List all doctors")
    @GetMapping
    public List<Doctor> list() {
        return doctorService.findAll();
    }

    @Operation(summary = "Get doctor by id")
    @GetMapping("/{id}")
    public Doctor get(@PathVariable Long id) {
        return doctorService.getById(id);
    }

    @Operation(summary = "Register a doctor")
    @PostMapping
    public ResponseEntity<Doctor> create(@Valid @RequestBody Doctor doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.create(doctor));
    }

    @Operation(summary = "Update doctor")
    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor patch) {
        return doctorService.update(id, patch);
    }

    @Operation(summary = "Delete doctor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
