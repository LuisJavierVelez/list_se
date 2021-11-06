package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Clase generada con el fin manejar paises, departamente y ciudades en un objeto
 * Ejemplo: code :169 description: Colombia
 * Ejemplo2 : code:16917 description: Caldas
 * Ejemplo3 : code:16917001 description: Manizales
 * @author Luis Javier Velez Uribe
 * @version 1.0 04-11-2021
 */

@Data
@AllArgsConstructor
public class Location {
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String description;
}
