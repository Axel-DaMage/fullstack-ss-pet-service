package com.sanosysalvos.petservice.repository;

import com.sanosysalvos.petservice.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByCorreo(String correo);

    Optional<Contact> findByTelefono(String telefono);
}