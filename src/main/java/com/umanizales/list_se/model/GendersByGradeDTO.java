package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GendersByGradeDTO {
     private byte grade;
     private List<CountByGenderDTO> gender;
     private int total;


}
