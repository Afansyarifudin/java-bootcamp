package com.example.springwebrest_ch5.migration;

import com.example.springwebrest_ch5.model.ERole;
import com.example.springwebrest_ch5.model.Role;
import com.example.springwebrest_ch5.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleMigration implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
    }

    private void initializeRoles() {
        for (ERole eRole : ERole.values()) {
            if (!roleRepository.existsByName(eRole)) {
                roleRepository.save(new Role().setName(eRole));
            }
        }
    }
}
