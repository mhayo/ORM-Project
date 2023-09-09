package org.akad.mhayo.orm_project.mybatis_module.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private long id;
    private String description;

    private double price;

}
