package com.sanosysalvos.petservice.service;

import com.sanosysalvos.petservice.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetFactory {

    public Pet createPet(String name, String race, String color, String size, String status) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setRace(race);
        pet.setColor(color);
        pet.setSize(size);
        pet.setStatus(status);
        return pet;
    }

    public Pet createLostPet(String name, String race, String color, String size, String description) {
        Pet pet = createPet(name, race, color, size, "LOST");
        pet.setDescription(description);
        return pet;
    }

    public Pet createFoundPet(String name, String race, String color, String size, String description) {
        Pet pet = createPet(name, race, color, size, "FOUND");
        pet.setDescription(description);
        return pet;
    }
}