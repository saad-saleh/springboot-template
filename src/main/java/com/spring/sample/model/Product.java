package com.spring.sample.model;


import lombok.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", length = 256, unique = true)
    private String sku;

    @Column(name = "name", length = 256)
    private String name;

    @Column(name = "price", precision = 20)
    private double price;

}
