package com.example.challenge_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class Order extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String order_time;
    private String destination_address;
    private Boolean completed;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
