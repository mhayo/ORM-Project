package org.akad.mhayo.orm_project.jdbc_module.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Customer {


    private long id;

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
