package com.example.atelier3.Repositories;

import com.example.atelier3.Entites.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains(String kw, Pageable pageable);
    Page<Patient> findByNomContainsAndMalade(String kw, boolean malade, Pageable pageable);
    Page<Patient> findByNomContainsAndDateNaissance(String kw, Date dateNaissance, Pageable pageable);
    Page<Patient> findByNomContainsAndMaladeAndDateNaissance(String kw, boolean malade, Date dateNaissance, Pageable pageable);
}
