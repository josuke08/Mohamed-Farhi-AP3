package com.example.atelier3;

import com.example.atelier3.Entites.Patient;
import com.example.atelier3.Repositories.PatientRepository;
import com.example.atelier3.Security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                    new Patient(null, "manal", new Date(), false, 120));
            patientRepository.save(
                    new Patient(null, "Mohamed", new Date(), true, 120));
            patientRepository.save(
                    new Patient(null, "Yazid", new Date(), true, 120));
            patientRepository.save(
                    new Patient(null, "salomon", new Date(), false, 120));
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
            securityService.saveNewUser("mohamed", "1234", "1234");
            securityService.saveNewUser("momo", "1234", "1234");
            securityService.saveNewUser("sut", "1234", "1234");

            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");

            securityService.addRoleToUser("mohamed", "USER");
            securityService.addRoleToUser("mohamed", "ADMIN");
            securityService.addRoleToUser("momo", "USER");
            securityService.addRoleToUser("sut", "USER");
        };
    }

}

