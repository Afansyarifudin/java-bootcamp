package com.example.springwebrest_ch5.repository;

import com.example.springwebrest_ch5.model.ERole;
import com.example.springwebrest_ch5.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByName(ERole name);
}
