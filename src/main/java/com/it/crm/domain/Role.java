package com.it.crm.domain;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Role {
    private Long id;

    private String sn;

    private String name;

    private List<Permission> permission = new ArrayList<>();

}