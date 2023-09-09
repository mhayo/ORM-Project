package org.akad.mhayo.orm_project.mybatis_module.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerDTO {

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

}
