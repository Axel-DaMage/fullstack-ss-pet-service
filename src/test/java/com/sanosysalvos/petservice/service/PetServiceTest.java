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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepositoryMock;
    @Mock
    private ContactRepository contactRepositoryMock;
    @Mock
    private PetFactory petFactoryMock;

    private PetService petService;

    private Pet mascota;
    private Contact contacto;

    @BeforeEach
    void setUp() {
        petService = new PetService(petRepositoryMock, contactRepositoryMock, petFactoryMock);

        mascota = new Pet();
        mascota.setId(1L);
        mascota.setName("Max");
        mascota.setRace("Golden Retriever");
        mascota.setColor("Dorado");
        mascota.setSize("Grande");
        mascota.setStatus("PERDIDO");

        contacto = new Contact();
        contacto.setId(1L);
        contacto.setName("Juan Perez");
        contacto.setPhone("+56912345678");
    }

    @Test
    void obtenerTodos_DeberiaRetornarTodasLasMascotas() {
        when(petRepositoryMock.findAll()).thenReturn(List.of(mascota));

        List<Pet> resultado = petService.getAllPets();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Max", resultado.get(0).getName());
        verify(petRepositoryMock).findAll();
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarMascota() {
        when(petRepositoryMock.findById(1L)).thenReturn(Optional.of(mascota));

        Optional<Pet> resultado = petService.getPetById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Max", resultado.get().getName());
        verify(petRepositoryMock).findById(1L);
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarOptionalVacio() {
        when(petRepositoryMock.findById(99L)).thenReturn(Optional.empty());

        Optional<Pet> resultado = petService.getPetById(99L);

        assertTrue(resultado.isEmpty());
        verify(petRepositoryMock).findById(99L);
    }

    @Test
    void crearMascota_DeberiaGuardarYRetornar() {
        when(petRepositoryMock.save(any(Pet.class))).thenReturn(mascota);

        Pet resultado = petService.createPet(mascota);

        assertNotNull(resultado);
        assertEquals("Max", resultado.getName());
        verify(petRepositoryMock).save(mascota);
    }

    @Test
    void crearMascotaConContacto_DeberiaGuardarAmbos() {
        when(contactRepositoryMock.save(any(Contact.class))).thenReturn(contacto);
        when(petRepositoryMock.save(any(Pet.class))).thenReturn(mascota);

        Pet resultado = petService.createPetWithContact(mascota, contacto);

        assertNotNull(resultado);
        assertEquals("Max", resultado.getName());
        assertEquals(contacto, mascota.getContact());
        verify(contactRepositoryMock).save(contacto);
        verify(petRepositoryMock).save(mascota);
    }

    @Test
    void actualizarMascota_CuandoExiste_DeberiaActualizarCampos() {
        Pet existente = new Pet();
        existente.setId(1L);
        existente.setName("Original");
        existente.setRace("Original");
        existente.setColor("Original");
        existente.setSize("Original");
        existente.setStatus("PERDIDO");

        Pet actualizado = new Pet();
        actualizado.setName("Max Modificado");
        actualizado.setRace("Labrador");
        actualizado.setColor("Negro");
        actualizado.setSize("Mediano");
        actualizado.setStatus("ENCONTRADO");
        actualizado.setDescription("Encontrado en parque");
        actualizado.setPhotoUrl("http://foto.com");

        when(petRepositoryMock.findById(1L)).thenReturn(Optional.of(existente));
        when(petRepositoryMock.save(any(Pet.class))).thenAnswer(inv -> inv.getArgument(0));

        Pet resultado = petService.updatePet(1L, actualizado);

        assertEquals("Max Modificado", resultado.getName());
        assertEquals("Labrador", resultado.getRace());
        assertEquals("Negro", resultado.getColor());
        assertEquals("Mediano", resultado.getSize());
        assertEquals("ENCONTRADO", resultado.getStatus());
        assertEquals("Encontrado en parque", resultado.getDescription());
        assertEquals("http://foto.com", resultado.getPhotoUrl());
        verify(petRepositoryMock).findById(1L);
        verify(petRepositoryMock).save(existente);
    }

    @Test
    void actualizarMascota_CuandoNoExiste_DeberiaLanzarExcepcion() {
        when(petRepositoryMock.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> petService.updatePet(99L, new Pet()));
        verify(petRepositoryMock).findById(99L);
        verify(petRepositoryMock, never()).save(any());
    }

    @Test
    void eliminarMascota_CuandoExiste_DeberiaEliminar() {
        when(petRepositoryMock.findById(1L)).thenReturn(Optional.of(mascota));

        petService.deletePet(1L);

        verify(petRepositoryMock).findById(1L);
        verify(petRepositoryMock).delete(mascota);
    }

    @Test
    void eliminarMascota_CuandoNoExiste_DeberiaLanzarExcepcion() {
        when(petRepositoryMock.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> petService.deletePet(99L));
        verify(petRepositoryMock).findById(99L);
        verify(petRepositoryMock, never()).delete(any());
    }

    @Test
    void buscarPorRaza_DeberiaRetornarFiltradas() {
        when(petRepositoryMock.findByRace("Labrador")).thenReturn(List.of(mascota));

        List<Pet> resultado = petService.getPetsByRace("Labrador");

        assertEquals(1, resultado.size());
        verify(petRepositoryMock).findByRace("Labrador");
    }

    @Test
    void buscarPorEstado_DeberiaRetornarFiltradas() {
        when(petRepositoryMock.findByStatus("PERDIDO")).thenReturn(List.of(mascota));

        List<Pet> resultado = petService.getPetsByStatus("PERDIDO");

        assertEquals(1, resultado.size());
        verify(petRepositoryMock).findByStatus("PERDIDO");
    }

    @Test
    void buscarPorColor_DeberiaRetornarFiltradas() {
        when(petRepositoryMock.findByColor("Dorado")).thenReturn(List.of(mascota));

        List<Pet> resultado = petService.getPetsByColor("Dorado");

        assertEquals(1, resultado.size());
        verify(petRepositoryMock).findByColor("Dorado");
    }

    @Test
    void contarPorEstado_DeberiaRetornarCantidad() {
        when(petRepositoryMock.countByStatus("PERDIDO")).thenReturn(5L);

        long resultado = petService.countPetsByStatus("PERDIDO");

        assertEquals(5L, resultado);
        verify(petRepositoryMock).countByStatus("PERDIDO");
    }

    @Test
    void crearMascotaDesdeFactory_DeberiaDelegarEnFactory() {
        Pet mascotaFactory = new Pet();
        mascotaFactory.setName("Factory");
        when(petFactoryMock.createPet("Factory", "Raza", "Color", "Grande", "PERDIDO"))
                .thenReturn(mascotaFactory);

        Pet resultado = petService.createPetFromFactory("Factory", "Raza", "Color", "Grande", "PERDIDO");

        assertNotNull(resultado);
        assertEquals("Factory", resultado.getName());
        verify(petFactoryMock).createPet("Factory", "Raza", "Color", "Grande", "PERDIDO");
    }
}
