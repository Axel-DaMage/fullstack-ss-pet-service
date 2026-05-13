package com.sanosysalvos.petservice.factory;

import com.sanosysalvos.petservice.entity.PetReport;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PetReportFactory {
    
    public PetReport createReport(String type, Map<String, Object> data) {
        PetReport report = new PetReport();
        report.setType(type);
        report.setSpecies(data.getOrDefault("species", "UNKNOWN").toString());
        report.setColor(data.getOrDefault("color", "UNKNOWN").toString());
        report.setBreed(data.getOrDefault("breed", "UNKNOWN").toString());
        
        try {
            report.setLat(Double.parseDouble(data.getOrDefault("lat", "0").toString()));
            report.setLng(Double.parseDouble(data.getOrDefault("lng", "0").toString()));
        } catch (Exception e) {
            report.setLat(0.0);
            report.setLng(0.0);
        }

        String contact = data.getOrDefault("contactInfo", "No info").toString();
        
        // Business rule simulation
        if ("FOUND".equalsIgnoreCase(type) && (contact.equals("No info") || contact.isEmpty())) {
            contact = "Anonimo";
        }
        report.setContactInfo(contact);
        
        return report;
    }
}
