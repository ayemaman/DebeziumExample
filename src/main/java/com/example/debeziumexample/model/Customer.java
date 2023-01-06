package com.example.debeziumexample.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "customers", schema = "inventory")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "inventory.customers_id_seq")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
