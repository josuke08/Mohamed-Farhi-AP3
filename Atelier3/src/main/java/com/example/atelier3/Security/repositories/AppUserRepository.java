package com.example.atelier3.Security.repositories;

import com.example.atelier3.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);

}
