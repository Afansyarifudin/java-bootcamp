package com.example.challenge_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders;

}
