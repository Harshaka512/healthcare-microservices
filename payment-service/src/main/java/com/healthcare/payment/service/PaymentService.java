package com.healthcare.payment.service;

import com.healthcare.payment.exception.PaymentNotFoundException;
import com.healthcare.payment.model.Payment;
import com.healthcare.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment getById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
    }

    @Transactional
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment update(Long id, Payment patch) {
        Payment existing = getById(id);
        if (patch.getAppointmentId() != null) {
            existing.setAppointmentId(patch.getAppointmentId());
        }
        if (patch.getAmount() != null) {
            existing.setAmount(patch.getAmount());
        }
        if (patch.getStatus() != null) {
            existing.setStatus(patch.getStatus());
        }
        return paymentRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException(id);
        }
        paymentRepository.deleteById(id);
    }
}
