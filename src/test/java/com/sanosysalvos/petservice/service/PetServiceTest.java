package com.sanosysalvos.petservice.service;

import com.sanosysalvos.petservice.model.Contact;
import com.sanosysalvos.petservice.model.Pet;
import com.sanosysalvos.petservice.repository.ContactRepository;
import com.sanosysalvos.petservice.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private PetFactory petFactory;

    private PetService petService;

    @BeforeEach
    void setUp() {
        petService = new PetService(petRepository, contactRepository, petFactory);
    }

    @Test
    void getAllPets_ShouldReturnAll() {
        Pet pet = new Pet();
        pet.setId(1L);
        when(petRepository.findAll()).thenReturn(List.of(pet));

        List<Pet> result = petService.getAllPets();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(petRepository).findAll();
    }

    @Test
    void getPetById_ShouldReturnPet() {
        Pet pet = new Pet();
        pet.setId(1L);
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> result = petService.getPetById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void getPetById_ShouldThrowWhenNotFound() {
        when(petRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Pet> result = petService.getPetById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void createPet_ShouldSaveAndReturn() {
        Pet pet = new Pet();
        pet.setNombre("Max");
        when(petRepository.save(pet)).thenReturn(pet);

        Pet result = petService.createPet(pet);

        assertNotNull(result);
        assertEquals("Max", result.getNombre());
        verify(petRepository).save(pet);
    }

    @Test
    void updatePet_ShouldUpdateAndReturn() {
        Pet existing = new Pet();
        existing.setId(1L);
        existing.setNombre("Old Name");

        Pet details = new Pet();
        details.setNombre("New Name");
        details.setRaza("Labrador");
        details.setColor("Negro");
        details.setTamano("Grande");
        details.setEstado("PERDIDO");
        details.setDescripcion("Updated desc");
        details.setFotoUrl("http://foto.jpg");

        when(petRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(petRepository.save(existing)).thenReturn(existing);

        Pet result = petService.updatePet(1L, details);

        assertEquals("New Name", result.getNombre());
        assertEquals("Labrador", result.getRaza());
        assertEquals("Negro", result.getColor());
        assertEquals("Grande", result.getTamano());
        assertEquals("PERDIDO", result.getEstado());
        assertEquals("Updated desc", result.getDescripcion());
        assertEquals("http://foto.jpg", result.getFotoUrl());
    }

    @Test
    void updatePet_ShouldThrowWhenNotFound() {
        when(petRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> petService.updatePet(99L, new Pet()));
    }

    @Test
    void deletePet_ShouldDelete() {
        Pet pet = new Pet();
        pet.setId(1L);
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        doNothing().when(petRepository).delete(pet);

        petService.deletePet(1L);

        verify(petRepository).delete(pet);
    }

    @Test
    void deletePet_ShouldThrowWhenNotFound() {
        when(petRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> petService.deletePet(99L));
    }

    @Test
    void findByStatus_ShouldReturnFiltered() {
        Pet pet = new Pet();
        pet.setEstado("PERDIDO");
        when(petRepository.findByEstado("PERDIDO")).thenReturn(List.of(pet));

        List<Pet> result = petService.getPetsByStatus("PERDIDO");

        assertEquals(1, result.size());
        assertEquals("PERDIDO", result.get(0).getEstado());
    }

    @Test
    void findByRace_ShouldReturnFiltered() {
        Pet pet = new Pet();
        pet.setRaza("Labrador");
        when(petRepository.findByRaza("Labrador")).thenReturn(List.of(pet));

        List<Pet> result = petService.getPetsByRace("Labrador");

        assertEquals(1, result.size());
        assertEquals("Labrador", result.get(0).getRaza());
    }

    @Test
    void findByColor_ShouldReturnFiltered() {
        Pet pet = new Pet();
        pet.setColor("Negro");
        when(petRepository.findByColor("Negro")).thenReturn(List.of(pet));

        List<Pet> result = petService.getPetsByColor("Negro");

        assertEquals(1, result.size());
        assertEquals("Negro", result.get(0).getColor());
    }

    @Test
    void getTotalsByStatus_ShouldReturnCounts() {
        when(petRepository.countByEstado("PERDIDO")).thenReturn(5L);
        when(petRepository.countByEstado("ENCONTRADO")).thenReturn(3L);

        long lost = petService.countPetsByStatus("PERDIDO");
        long found = petService.countPetsByStatus("ENCONTRADO");

        assertEquals(5L, lost);
        assertEquals(3L, found);
    }

    @Test
    void createPetWithContact_ShouldSaveBothInTransaction() {
        Pet pet = new Pet();
        pet.setNombre("Max");

        Contact contact = new Contact();
        contact.setNombre("Juan");
        contact.setTelefono("123456789");
        contact.setCorreo("juan@test.com");

        Contact savedContact = new Contact();
        savedContact.setId(1L);
        savedContact.setNombre("Juan");

        Pet savedPet = new Pet();
        savedPet.setId(1L);
        savedPet.setNombre("Max");
        savedPet.setContacto(savedContact);

        when(contactRepository.save(contact)).thenReturn(savedContact);
        when(petRepository.save(pet)).thenReturn(savedPet);

        Pet result = petService.createPetWithContact(pet, contact);

        assertNotNull(result);
        assertEquals("Max", result.getNombre());
        assertNotNull(result.getContacto());
        assertEquals("Juan", result.getContacto().getNombre());
        verify(contactRepository).save(contact);
        verify(petRepository).save(pet);
    }
}
