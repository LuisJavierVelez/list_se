package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GradesByLocationDTO {

    private Location location;
    private List<GendersByGradeDTO> grades;
}
