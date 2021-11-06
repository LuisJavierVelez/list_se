package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Clase encargade de manejar el grado academico en el que se encuentra el niño
 * Ejemplo: primero(1), Segundo(2), Tercero(3) en este caso se toma el numero del grado como referencia
 * @author Luis Javier Velez Uribe
 * @version 1.0 05-11-2021
 */

@Data
@AllArgsConstructor
public class Degree {
    @NotNull
    @NotEmpty
    private Integer degree;

}
