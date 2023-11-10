package com.example.springwebrest_ch5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Order extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String order_time;
    private String destination_address;
    private boolean completed;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

}
