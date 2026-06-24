package com.sanosysalvos.petservice.controller;

import com.sanosysalvos.petservice.model.Pet;
import com.sanosysalvos.petservice.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    void getAllPets_ShouldReturnList() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setNombre("Max");
        when(petService.getAllPets()).thenReturn(List.of(pet));

        mockMvc.perform(get("/api/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Max"));
    }

    @Test
    void getPetById_ShouldReturnPet() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setNombre("Max");
        when(petService.getPetById(1L)).thenReturn(Optional.of(pet));

        mockMvc.perform(get("/api/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Max"));
    }

    @Test
    void getPetById_ShouldReturn404WhenNotFound() throws Exception {
        when(petService.getPetById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pets/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPet_ShouldReturn201() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setNombre("Max");
        when(petService.createPet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Max\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Max"));
    }

    @Test
    void updatePet_ShouldReturnOk() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setNombre("Max Updated");
        when(petService.updatePet(eq(1L), any(Pet.class))).thenReturn(pet);

        mockMvc.perform(put("/api/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Max Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Max Updated"));
    }

    @Test
    void updatePet_ShouldReturn404WhenNotFound() throws Exception {
        when(petService.updatePet(eq(99L), any(Pet.class))).thenThrow(new RuntimeException("Pet not found"));

        mockMvc.perform(put("/api/pets/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Max\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePet_ShouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/pets/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePet_ShouldReturn404WhenNotFound() throws Exception {
        doThrow(new RuntimeException("Pet not found")).when(petService).deletePet(99L);

        mockMvc.perform(delete("/api/pets/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchByRace_ShouldReturnFiltered() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setRaza("Labrador");
        when(petService.getPetsByRace("Labrador")).thenReturn(List.of(pet));

        mockMvc.perform(get("/api/pets/search/race/Labrador"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].raza").value("Labrador"));
    }

    @Test
    void searchByStatus_ShouldReturnFiltered() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setEstado("PERDIDO");
        when(petService.getPetsByStatus("PERDIDO")).thenReturn(List.of(pet));

        mockMvc.perform(get("/api/pets/search/status/PERDIDO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("PERDIDO"));
    }

    @Test
    void searchByColor_ShouldReturnFiltered() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setColor("Negro");
        when(petService.getPetsByColor("Negro")).thenReturn(List.of(pet));

        mockMvc.perform(get("/api/pets/search/color/Negro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color").value("Negro"));
    }

    @Test
    void getTotalsByStatus_ShouldReturnCounts() throws Exception {
        when(petService.countPetsByStatus("PERDIDO")).thenReturn(5L);
        when(petService.countPetsByStatus("ENCONTRADO")).thenReturn(3L);

        mockMvc.perform(get("/api/pets/totals/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.perdido").value(5))
                .andExpect(jsonPath("$.encontrado").value(3));
    }
}
