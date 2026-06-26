package com.sanosysalvos.petservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanosysalvos.petservice.model.Pet;
import com.sanosysalvos.petservice.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private PetService petServiceMock;

    private final ObjectMapper mapper = new ObjectMapper();

    private Pet crearMascota(Long id, String nombre, String estado) {
        Pet p = new Pet();
        p.setId(id);
        p.setName(nombre);
        p.setRace("Labrador");
        p.setColor("Negro");
        p.setSize("Grande");
        p.setStatus(estado);
        return p;
    }

    @Test
    void obtenerTodas_DeberiaRetornarLista200() throws Exception {
        when(petServiceMock.getAllPets()).thenReturn(List.of(crearMascota(1L, "Max", "PERDIDO")));

        mvc.perform(get("/api/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Max"));
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornar200() throws Exception {
        when(petServiceMock.getPetById(1L)).thenReturn(Optional.of(crearMascota(1L, "Max", "PERDIDO")));

        mvc.perform(get("/api/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Max"));
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornar404() throws Exception {
        when(petServiceMock.getPetById(99L)).thenReturn(Optional.empty());

        mvc.perform(get("/api/pets/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearMascota_DeberiaRetornar201() throws Exception {
        Pet mascota = crearMascota(null, "Nueva", "PERDIDO");
        Pet mascotaConId = crearMascota(1L, "Nueva", "PERDIDO");
        when(petServiceMock.createPet(any(Pet.class))).thenReturn(mascotaConId);

        mvc.perform(post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mascota)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Nueva"));
    }

    @Test
    void actualizarMascota_CuandoExiste_DeberiaRetornar200() throws Exception {
        Pet actualizada = crearMascota(1L, "Modificado", "ENCONTRADO");
        when(petServiceMock.updatePet(eq(1L), any(Pet.class))).thenReturn(actualizada);

        mvc.perform(put("/api/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Modificado"));
    }

    @Test
    void actualizarMascota_CuandoNoExiste_DeberiaRetornar404() throws Exception {
        when(petServiceMock.updatePet(eq(99L), any(Pet.class)))
                .thenThrow(new RuntimeException("not found"));

        mvc.perform(put("/api/pets/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new Pet())))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarMascota_CuandoExiste_DeberiaRetornar204() throws Exception {
        mvc.perform(delete("/api/pets/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarMascota_CuandoNoExiste_DeberiaRetornar404() throws Exception {
        when(petServiceMock.deletePet(99L)).thenThrow(new RuntimeException("not found"));

        mvc.perform(delete("/api/pets/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorRaza_DeberiaRetornar200() throws Exception {
        when(petServiceMock.getPetsByRace("Labrador"))
                .thenReturn(List.of(crearMascota(1L, "Max", "PERDIDO")));

        mvc.perform(get("/api/pets/search/race/Labrador"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].race").value("Labrador"));
    }

    @Test
    void buscarPorEstado_DeberiaRetornar200() throws Exception {
        when(petServiceMock.getPetsByStatus("PERDIDO"))
                .thenReturn(List.of(crearMascota(1L, "Max", "PERDIDO")));

        mvc.perform(get("/api/pets/search/status/PERDIDO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("PERDIDO"));
    }

    @Test
    void buscarPorColor_DeberiaRetornar200() throws Exception {
        when(petServiceMock.getPetsByColor("Negro"))
                .thenReturn(List.of(crearMascota(1L, "Max", "PERDIDO")));

        mvc.perform(get("/api/pets/search/color/Negro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color").value("Negro"));
    }

    @Test
    void obtenerTotalesPorEstado_DeberiaRetornarConteos() throws Exception {
        when(petServiceMock.countPetsByStatus("PERDIDO")).thenReturn(3L);
        when(petServiceMock.countPetsByStatus("ENCONTRADO")).thenReturn(2L);

        mvc.perform(get("/api/pets/totals/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.perdido").value(3))
                .andExpect(jsonPath("$.encontrado").value(2));
    }

    @Test
    void crearMascotaConContacto_DeberiaRetornar201() throws Exception {
        Pet mascota = crearMascota(1L, "ConContacto", "PERDIDO");
        when(petServiceMock.createPetWithContact(any(Pet.class), any())).thenReturn(mascota);

        Map<String, Object> body = Map.of(
                "name", "ConContacto",
                "race", "Labrador",
                "color", "Negro",
                "size", "Grande",
                "status", "PERDIDO",
                "contactName", "Juan",
                "contactPhone", "+56912345678"
        );

        mvc.perform(post("/api/pets/with-contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("ConContacto"));
    }
}
