package com.umanizales.list_se.model;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase creada con el fin de dar respuesta al metodo que nos pregunta cuantos niños hay por locación, en esta clase se encuentra el contadaor
 * encargado de ir contando cada una de las ciudades a las que pertenecen los niños.
 * @author  Luis Javier Velez Uribe
 * @version 1.0 04-11-2021
 */

@Data
@AllArgsConstructor
public class BoysByLocation {
    private Location location;
    private int count;
}
