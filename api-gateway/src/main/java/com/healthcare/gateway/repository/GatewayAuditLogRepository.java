package com.healthcare.gateway.repository;

import com.healthcare.gateway.model.GatewayAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayAuditLogRepository extends JpaRepository<GatewayAuditLog, Long> {
}
