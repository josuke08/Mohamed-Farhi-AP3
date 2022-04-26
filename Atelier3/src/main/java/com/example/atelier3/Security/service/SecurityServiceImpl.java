package com.example.atelier3.Security.service;

import com.example.atelier3.Security.entities.AppRole;
import com.example.atelier3.Security.entities.AppUser;
import com.example.atelier3.Security.repositories.AppRoleRepository;
import com.example.atelier3.Security.repositories.AppUserRepository;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password doesn't match ");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole != null) throw new RuntimeException("Role " + roleName + " already exists");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new RuntimeException("User " + username + " doesn't exists");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole == null) throw new RuntimeException("Role " + roleName + " doesn't exists");

        if(!appUser.containsRole(appRole))
            appUser.getAppRoles().add(appRole);

    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new RuntimeException("User " + username + " already exists");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole == null) throw new RuntimeException("Role " + roleName + " already exists");

        appUser.getAppRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return appUserRepository.findByUsername(username);
    }
}
