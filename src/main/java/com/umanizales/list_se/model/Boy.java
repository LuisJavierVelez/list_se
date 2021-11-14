package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Clase encargada de almacenar la informacion relacionada con un niño
 * Tiene campos obligatorios para (Edad, identificacion,nombre, genero, grado y localizacion)
 * @author Luis Javier Velez Velez
 * @version 1.0 - 04-11-2021
 */

@Data
@AllArgsConstructor
public class Boy {
    @NotNull
    @NotEmpty
    @Size(min=2)
    private String identification;
    @NotNull
    @NotEmpty
    @Size(min=2, max = 50)
    private String name;
    @Positive
    private byte age;
    @NotNull
    private Gender gender;
    @Valid
    @NotNull
    private Location location;
    @NotNull
    private Degree degree;
    @NotNull
    private boolean orphans;
    @NotNull
    @Size(min =1 ,max=5)
    private byte grade;
    @NotNull
    private String rh;


}
