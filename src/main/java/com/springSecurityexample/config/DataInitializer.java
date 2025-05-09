package com.springSecurityexample.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.springSecurityexample.model.AppUser;
import com.springSecurityexample.model.Role;
import com.springSecurityexample.repository.AppUserRepository;
import com.springSecurityexample.repository.RoleRepository;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role adminRole = roleRepository.save(new Role(null, "ROLE_ADMIN"));
        Role userRole = roleRepository.save(new Role(null, "ROLE_USER"));

        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEnabled(true);
        admin.setRoles(Set.of(adminRole));
        appUserRepository.save(admin);

        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setEnabled(true);
        user.setRoles(Set.of(userRole));
        appUserRepository.save(user);
    }
}


