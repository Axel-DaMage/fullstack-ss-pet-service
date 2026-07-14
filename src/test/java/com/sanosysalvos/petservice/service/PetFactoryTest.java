package com.sanosysalvos.petservice.service;

import com.sanosysalvos.petservice.model.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetFactoryTest {

    private final PetFactory petFactory = new PetFactory();

    @Test
    void testCreatePet() {
        Pet pet = petFactory.createPet("Max", "Golden Retriever", "Dorado", "Grande", "PERDIDO");

        assertNotNull(pet);
        assertEquals("Max", pet.getNombre());
        assertEquals("Golden Retriever", pet.getRaza());
        assertEquals("Dorado", pet.getColor());
        assertEquals("Grande", pet.getTamano());
        assertEquals("PERDIDO", pet.getEstado());
    }

    @Test
    void testCreateLostPet() {
        Pet pet = petFactory.createLostPet("Luna", "Siames", "Crema", "Mediano", "Gata perdida");

        assertNotNull(pet);
        assertEquals("Luna", pet.getNombre());
        assertEquals("PERDIDO", pet.getEstado());
        assertEquals("Gata perdida", pet.getDescripcion());
    }

    @Test
    void testCreateFoundPet() {
        Pet pet = petFactory.createFoundPet("Rocky", "Pastor Aleman", "Marron", "Grande", "Encontrado en parque");

        assertNotNull(pet);
        assertEquals("Rocky", pet.getNombre());
        assertEquals("ENCONTRADO", pet.getEstado());
        assertEquals("Encontrado en parque", pet.getDescripcion());
    }

    @Test
    void testCreateLostPet_SetsStatusPerdido() {
        Pet pet = petFactory.createLostPet("Luna", "Siames", "Crema", "Mediano", "Gata perdida");

        assertEquals("PERDIDO", pet.getEstado());
        assertEquals("Gata perdida", pet.getDescripcion());
    }

    @Test
    void testCreateFoundPet_SetsStatusEncontrado() {
        Pet pet = petFactory.createFoundPet("Rocky", "Pastor Aleman", "Marron", "Grande", "Encontrado en parque");

        assertEquals("ENCONTRADO", pet.getEstado());
        assertEquals("Encontrado en parque", pet.getDescripcion());
    }
}