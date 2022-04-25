package com.example.atelier3.Security.service;

import com.example.atelier3.Security.entities.AppRole;
import com.example.atelier3.Security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String uername, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleToUser(String username, String roleName);
    AppUser loadUserByUserName(String username);
}
