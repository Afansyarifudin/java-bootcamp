package com.example.springwebrest_ch5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "merchant")
@SQLDelete(sql = "UPDATE merchant SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Merchant extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty
    private String name;
    private String location;
    private boolean open;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant")
    private List<Product> productList;
}
