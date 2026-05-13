package com.sanosysalvos.petservice.controller;

import com.sanosysalvos.petservice.entity.PetReport;
import com.sanosysalvos.petservice.factory.PetReportFactory;
import com.sanosysalvos.petservice.repository.PetReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class PetReportController {

    @Autowired
    private PetReportRepository repository;

    @Autowired
    private PetReportFactory factory;

    @GetMapping("/")
    public List<PetReport> getAllReports() {
        return repository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<PetReport> createReport(@RequestBody Map<String, Object> data) {
        String type = data.getOrDefault("type", "").toString();
        if (type.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PetReport report = factory.createReport(type, data);
        PetReport saved = repository.save(report);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/health")
    public String health() {
        return "Pet Service is running";
    }
}
