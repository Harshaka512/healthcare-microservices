package com.healthcare.gateway.controller;

import com.healthcare.gateway.model.GatewayAuditLog;
import com.healthcare.gateway.service.GatewayAuditLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping("/api/gateway/audit-logs")
public class GatewayAuditLogController {

    private final GatewayAuditLogService gatewayAuditLogService;

    public GatewayAuditLogController(GatewayAuditLogService gatewayAuditLogService) {
        this.gatewayAuditLogService = gatewayAuditLogService;
    }

    @GetMapping
    public Mono<List<GatewayAuditLog>> list() {
        return Mono.fromCallable(gatewayAuditLogService::findAll).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/{id}")
    public Mono<GatewayAuditLog> get(@PathVariable Long id) {
        return Mono.fromCallable(() -> gatewayAuditLogService.getById(id)).subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping
    public Mono<ResponseEntity<GatewayAuditLog>> create(@RequestBody GatewayAuditLog log) {
        return Mono.fromCallable(() -> gatewayAuditLogService.create(log))
                .subscribeOn(Schedulers.boundedElastic())
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return Mono.fromRunnable(() -> gatewayAuditLogService.delete(id))
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(ResponseEntity.noContent().build());
    }
}
