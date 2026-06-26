package com.sanosysalvos.petservice.service;

import com.sanosysalvos.petservice.model.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetFactoryTest {

    private final PetFactory fabrica = new PetFactory();

    @Test
    void crearMascota_DeberiaAsignarTodosLosCampos() {
        Pet mascota = fabrica.createPet("Max", "Golden Retriever", "Dorado", "Grande", "PERDIDO");

        assertNotNull(mascota);
        assertEquals("Max", mascota.getName());
        assertEquals("Golden Retriever", mascota.getRace());
        assertEquals("Dorado", mascota.getColor());
        assertEquals("Grande", mascota.getSize());
        assertEquals("PERDIDO", mascota.getStatus());
    }

    @Test
    void crearMascotaPerdida_DeberiaTenerEstadoPERDIDO() {
        Pet mascota = fabrica.createLostPet("Luna", "Siames", "Crema", "Mediano", "Gata perdida en la noche");

        assertNotNull(mascota);
        assertEquals("Luna", mascota.getName());
        assertEquals("PERDIDO", mascota.getStatus());
        assertEquals("Gata perdida en la noche", mascota.getDescription());
    }

    @Test
    void crearMascotaEncontrada_DeberiaTenerEstadoENCONTRADO() {
        Pet mascota = fabrica.createFoundPet("Rocky", "Pitbull", "Negro", "Grande", "Encontrado en el parque");

        assertNotNull(mascota);
        assertEquals("Rocky", mascota.getName());
        assertEquals("ENCONTRADO", mascota.getStatus());
        assertEquals("Encontrado en el parque", mascota.getDescription());
    }

    @Test
    void crearMascotaPerdida_SinDescripcion_DeberiaTenerDescripcionNula() {
        Pet mascota = fabrica.createLostPet("Thor", "Labrador", "Negro", "Grande", null);

        assertNull(mascota.getDescription());
        assertEquals("PERDIDO", mascota.getStatus());
    }

    @Test
    void crearMascota_SinNombre_DeberiaCrearConNombreNulo() {
        Pet mascota = fabrica.createPet(null, "Raza", "Color", "Size", "PERDIDO");

        assertNull(mascota.getName());
        assertEquals("Raza", mascota.getRace());
    }
}
