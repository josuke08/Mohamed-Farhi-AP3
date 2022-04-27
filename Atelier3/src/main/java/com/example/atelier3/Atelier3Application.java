package com.example.atelier3;

import com.example.atelier3.Entites.Medecin;
import com.example.atelier3.Entites.Patient;
import com.example.atelier3.Repositories.MedecinRepository;
import com.example.atelier3.Repositories.PatientRepository;
import com.example.atelier3.Security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class Atelier3Application {

    public static void main(String[] args) {
        SpringApplication.run(Atelier3Application.class, args);
    }

    //@Bean
    CommandLineRunner CommandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null, "manal","waadmgsddal54", new Date(), false, 120));
            patientRepository.save(
                    new Patient(null, "Mohadmed", "waadmdddgsgl54", new Date(), true, 120));
            patientRepository.save(
                    new Patient(null, "Yazid", "waadfmqdlsgsg54", new Date(), true, 120));
            patientRepository.save(
                    new Patient(null, "salos", "waagddsgdmdl54", new Date(), false, 120));
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            /*securityService.saveNewUser("mohamed", "1234", "1234");
            securityService.saveNewUser("momo", "1234", "1234");
            securityService.saveNewUser("sut", "1234", "1234");

            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");*/

            securityService.addRoleToUser("mohamed", "USER");
            securityService.addRoleToUser("mohamed", "ADMIN");
            securityService.addRoleToUser("momo", "USER");
            securityService.addRoleToUser("sut", "USER");
        };
    }

    //@Bean
    CommandLineRunner CommandLineRunner(MedecinRepository medecinRepository) {
        return args -> {
            medecinRepository.save(
                new Medecin(null, "Hippocrate", "ksakdsl", "cardiologie"));
            medecinRepository.save(
                    new Medecin(null, "TrishPaytas", "kssadld", "cardiologie"));
            medecinRepository.save(
                    new Medecin(null, "Frank", "ksaqsld", "cardiologie"));
            medecinRepository.save(
                    new Medecin(null, "Sutton", "fassdl", "cardiologie"));
        };
    }

}

