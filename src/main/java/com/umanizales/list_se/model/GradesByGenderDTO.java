package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GradesByGenderDTO {
    private Gender gender;
    private RhByGradesDTO[] rhByGradesDTOS;

}
