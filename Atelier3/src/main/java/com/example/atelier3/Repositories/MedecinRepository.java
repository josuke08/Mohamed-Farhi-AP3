package com.example.atelier3.Repositories;

import com.example.atelier3.Entites.Medecin;
import com.example.atelier3.Entites.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Page<Medecin> findByNomContains(String kw, Pageable pageable);
}
