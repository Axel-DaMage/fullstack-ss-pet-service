package com.sanosysalvos.petservice.service;

import com.sanosysalvos.petservice.model.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetFactoryTest {

    private final PetFactory petFactory = new PetFactory();

    @Test
    void testCreatePet() {
        Pet pet = petFactory.createPet("Max", "Golden Retriever", "Dorado", "Grande", "LOST");

        assertNotNull(pet);
        assertEquals("Max", pet.getName());
        assertEquals("Golden Retriever", pet.getRace());
        assertEquals("Dorado", pet.getColor());
        assertEquals("Grande", pet.getSize());
        assertEquals("LOST", pet.getStatus());
    }

    @Test
    void testCreateLostPet() {
        Pet pet = petFactory.createLostPet("Luna", "Siames", "Crema", "Mediano", "Gata perdida");

        assertNotNull(pet);
        assertEquals("Luna", pet.getName());
        assertEquals("LOST", pet.getStatus());
        assertEquals("Gata perdida", pet.getDescription());
    }
}