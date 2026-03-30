package com.healthcare.payment.controller;

import com.healthcare.payment.model.Payment;
import com.healthcare.payment.service.PaymentService;
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
@RequestMapping("/payments")
@Tag(name = "Payments", description = "Payment API for appointments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "List all payments")
    @GetMapping
    public List<Payment> list() {
        return paymentService.findAll();
    }

    @Operation(summary = "Get payment by id")
    @GetMapping("/{id}")
    public Payment get(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @Operation(summary = "Create payment")
    @PostMapping
    public ResponseEntity<Payment> create(@Valid @RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(payment));
    }

    @Operation(summary = "Update payment")
    @PutMapping("/{id}")
    public Payment update(@PathVariable Long id, @RequestBody Payment patch) {
        return paymentService.update(id, patch);
    }

    @Operation(summary = "Delete payment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
