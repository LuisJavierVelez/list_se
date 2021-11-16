package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Clase generada con el fin de manejar generos de los niños mediante una descripcion
 * Ejemplo. Masculino y Femenino
 * @author Luis Javier Velez Uribe
 * @version 1.0 05-11-2021
 */

@Data
@AllArgsConstructor
public class Gender {
    @NotNull
    @NotEmpty
    private String Description;


}

