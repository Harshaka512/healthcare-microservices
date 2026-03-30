package com.healthcare.appointment.controller;

import com.healthcare.appointment.model.Appointment;
import com.healthcare.appointment.service.AppointmentService;
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
@RequestMapping("/appointments")
@Tag(name = "Appointments", description = "Appointment booking API")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "List all appointments")
    @GetMapping
    public List<Appointment> list() {
        return appointmentService.findAll();
    }

    @Operation(summary = "Get appointment by id")
    @GetMapping("/{id}")
    public Appointment get(@PathVariable Long id) {
        return appointmentService.getById(id);
    }

    @Operation(summary = "Create appointment")
    @PostMapping
    public ResponseEntity<Appointment> create(@Valid @RequestBody Appointment appointment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.create(appointment));
    }

    @Operation(summary = "Update appointment")
    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestBody Appointment patch) {
        return appointmentService.update(id, patch);
    }

    @Operation(summary = "Delete appointment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
