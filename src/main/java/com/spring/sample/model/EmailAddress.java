package com.spring.sample.model;

import lombok.*;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "email_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "address", length = 128)
    private String address;
}
