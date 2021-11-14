package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountByGenderDTO {

    private String gender;
    private int count;
}
