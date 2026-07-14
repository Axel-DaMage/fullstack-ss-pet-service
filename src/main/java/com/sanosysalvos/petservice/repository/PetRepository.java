package com.sanosysalvos.petservice.repository;

import com.sanosysalvos.petservice.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByEstado(String estado);

    List<Pet> findByRaza(String raza);

    List<Pet> findByColor(String color);

    List<Pet> findByTamano(String tamano);

    long countByEstado(String estado);
}