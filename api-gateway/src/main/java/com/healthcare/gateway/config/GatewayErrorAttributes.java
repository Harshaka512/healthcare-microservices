package com.healthcare.gateway.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GatewayErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> attributes = new LinkedHashMap<>(super.getErrorAttributes(request, options));
        attributes.put("source", "api-gateway");

        Throwable error = getError(request);
        if (error != null) {
            attributes.put("errorType", error.getClass().getSimpleName());
            Object msg = attributes.get("message");
            if (msg == null || (msg instanceof String s && s.isBlank())) {
                attributes.put("message", error.getMessage() != null ? error.getMessage() : "Gateway error");
            }
        }

        return attributes;
    }
}
