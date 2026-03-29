package com.healthcare.gateway.service;

import com.healthcare.gateway.model.GatewayAuditLog;
import com.healthcare.gateway.repository.GatewayAuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GatewayAuditLogService {

    private final GatewayAuditLogRepository repository;

    public GatewayAuditLogService(GatewayAuditLogRepository repository) {
        this.repository = repository;
    }

    public List<GatewayAuditLog> findAll() {
        return repository.findAll();
    }

    public GatewayAuditLog getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Audit log not found: " + id));
    }

    @Transactional
    public GatewayAuditLog create(GatewayAuditLog log) {
        return repository.save(log);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
