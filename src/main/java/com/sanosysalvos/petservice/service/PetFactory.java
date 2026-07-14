package com.sanosysalvos.petservice.service;

import com.sanosysalvos.petservice.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetFactory {

    public Pet createPet(String nombre, String raza, String color, String tamano, String estado) {
        Pet pet = new Pet();
        pet.setNombre(nombre);
        pet.setRaza(raza);
        pet.setColor(color);
        pet.setTamano(tamano);
        pet.setEstado(estado);
        return pet;
    }

    public Pet createLostPet(String nombre, String raza, String color, String tamano, String descripcion) {
        Pet pet = createPet(nombre, raza, color, tamano, "PERDIDO");
        pet.setDescripcion(descripcion);
        return pet;
    }

    public Pet createFoundPet(String nombre, String raza, String color, String tamano, String descripcion) {
        Pet pet = createPet(nombre, raza, color, tamano, "ENCONTRADO");
        pet.setDescripcion(descripcion);
        return pet;
    }
}