package com.sanosysalvos.petservice.repository;

import com.sanosysalvos.petservice.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByStatus(String status);

    List<Pet> findByRace(String race);

    List<Pet> findByColor(String color);

    List<Pet> findBySize(String size);

    long countByStatus(String status);
}