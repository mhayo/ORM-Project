package org.akad.mhayo.orm_project.mybatis_module.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {

    @JsonProperty
    private String username;

    @JsonProperty
    private long cartid;

    @JsonProperty
    private long itemid;

    @JsonProperty
    private int quantity;

}