package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase creada con el fin de dar respuesta al metodo que solicita obtener los niños de un grado escolar segun el indicado por paremetro
 * esta clase contiene un contador que va contando los niños que pertenecen a cada grado.
 * @author Luis Javier Velez Uribe
 * @version 1.0 05-11-2021
 */

@Data
@AllArgsConstructor
public class BoysByDegree {
    private Degree degree;
    private int count;
}
