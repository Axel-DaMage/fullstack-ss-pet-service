package com.sanosysalvos.petservice.controller;

import com.sanosysalvos.petservice.model.Contact;
import com.sanosysalvos.petservice.model.Pet;
import com.sanosysalvos.petservice.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet createdPet = petService.createPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    @PostMapping("/with-contact")
    public ResponseEntity<Pet> createPetWithContact(@RequestBody Map<String, Object> request) {
        Pet pet = new Pet();
        pet.setNombre((String) request.get("nombre"));
        pet.setRaza((String) request.get("raza"));
        pet.setColor((String) request.get("color"));
        pet.setTamano((String) request.get("tamano"));
        pet.setEstado((String) request.get("estado"));
        pet.setDescripcion((String) request.get("descripcion"));
        pet.setFotoUrl((String) request.get("fotoUrl"));

        Contact contact = new Contact();
        contact.setNombre((String) request.get("contactoNombre"));
        contact.setTelefono((String) request.get("contactoTelefono"));
        contact.setCorreo((String) request.get("contactoCorreo"));
        contact.setDireccion((String) request.get("contactoDireccion"));

        Pet createdPet = petService.createPetWithContact(pet, contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        try {
            Pet updatedPet = petService.updatePet(id, pet);
            return ResponseEntity.ok(updatedPet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/race/{race}")
    public ResponseEntity<List<Pet>> getPetsByRace(@PathVariable String race) {
        return ResponseEntity.ok(petService.getPetsByRace(race));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<Pet>> getPetsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(petService.getPetsByStatus(status));
    }

    @GetMapping("/search/color/{color}")
    public ResponseEntity<List<Pet>> getPetsByColor(@PathVariable String color) {
        return ResponseEntity.ok(petService.getPetsByColor(color));
    }

    @GetMapping("/totals/status")
    public ResponseEntity<Map<String, Long>> getTotalsByStatus() {
        long lostCount = petService.countPetsByStatus("PERDIDO");
        long foundCount = petService.countPetsByStatus("ENCONTRADO");
        return ResponseEntity.ok(Map.of("perdido", lostCount, "encontrado", foundCount));
    }
}