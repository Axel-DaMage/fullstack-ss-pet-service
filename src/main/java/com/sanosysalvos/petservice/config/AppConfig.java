package com.sanosysalvos.petservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class AppConfig {

    private static AppConfig instance;

    @Value("${spring.application.name:pet-service}")
    private String serviceName;

    @Value("${server.port:3001}")
    private int port;

    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/sanosysalvos}")
    private String databaseUrl;

    @Value("${logging.level.com.sanosysalvos:INFO}")
    private String logLevel;

    @Value("${service.external-api.url:}")
    private String externalApiUrl;

    @Value("${service.cache.enabled:true}")
    private boolean cacheEnabled;

    @Value("${service.cache.ttl:3600}")
    private int cacheTtl;

    private AppConfig() {
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            throw new IllegalStateException("AppConfig not initialized");
        }
        return instance;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getPort() {
        return port;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getExternalApiUrl() {
        return externalApiUrl;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public int getCacheTtl() {
        return cacheTtl;
    }

    public String getServiceInfo() {
        return String.format("Service: %s, Port: %d, Cache: %s",
            serviceName, port, cacheEnabled ? "enabled" : "disabled");
    }
}