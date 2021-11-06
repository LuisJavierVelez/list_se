package com.umanizales.list_se.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase creada para dar respuesta al metodo donde nos solicitan un conteo de los niños por genero
 * en esta clase encontramos el genero al que pertence el niño y el contador encargado de ir contanto cada niño.
 * @author Luis Javier Velez Uribe
 * @version 1.0 05-11-2021
 */

@Data
@AllArgsConstructor
public class BoysByGender {
    private Gender gender;
    private int count;

}
