package com.sanosysalvos.petservice.service;

import com.sanosysalvos.petservice.model.Contact;
import com.sanosysalvos.petservice.model.Pet;
import com.sanosysalvos.petservice.repository.ContactRepository;
import com.sanosysalvos.petservice.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final ContactRepository contactRepository;
    private final PetFactory petFactory;

    public PetService(PetRepository petRepository, ContactRepository contactRepository, PetFactory petFactory) {
        this.petRepository = petRepository;
        this.contactRepository = contactRepository;
        this.petFactory = petFactory;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Transactional
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional
    public Pet createPetWithContact(Pet pet, Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        pet.setContacto(savedContact);
        return petRepository.save(pet);
    }

    @Transactional
    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));

        pet.setNombre(petDetails.getNombre());
        pet.setRaza(petDetails.getRaza());
        pet.setColor(petDetails.getColor());
        pet.setTamano(petDetails.getTamano());
        pet.setEstado(petDetails.getEstado());
        pet.setDescripcion(petDetails.getDescripcion());
        pet.setFotoUrl(petDetails.getFotoUrl());

        return petRepository.save(pet);
    }

    @Transactional
    public void deletePet(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
        petRepository.delete(pet);
    }

    public List<Pet> getPetsByRace(String race) {
        return petRepository.findByRaza(race);
    }

    public List<Pet> getPetsByStatus(String status) {
        return petRepository.findByEstado(status);
    }

    public long countPetsByStatus(String status) {
        return petRepository.countByEstado(status);
    }

    public List<Pet> getPetsByColor(String color) {
        return petRepository.findByColor(color);
    }

    public Pet createPetFromFactory(String name, String race, String color, String size, String status) {
        return petFactory.createPet(name, race, color, size, status);
    }
}