package com.sanosysalvos.petservice.repository;

import com.sanosysalvos.petservice.entity.PetReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetReportRepository extends CrudRepository<PetReport, Long> {
    List<PetReport> findAll();
}
