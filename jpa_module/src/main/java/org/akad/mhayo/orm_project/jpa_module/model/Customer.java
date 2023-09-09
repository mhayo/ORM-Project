package org.akad.mhayo.orm_project.jpa_module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private String name;
    private String surname;

    private Date birthday;

    private String country;
    private String zipcode;
    private String city;
    private String street;
    private int housenumber;

    // standard constructor

    public Customer(){

    }

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Customer(long id) {
        this.id = id;
    }
}
