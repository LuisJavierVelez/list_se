package com.umanizales.list_se.controller.dto;

import com.umanizales.list_se.controller.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Esta clase esta definida para tener una estructura estandar de respuestas para responder de las mismas manera
 */
@Data
@AllArgsConstructor
public class ResponseDTO {
    /**
     * Estructura del mensaje que se reporta esta clase
     */
    private String message;
    /**
     * Se encuentra el contenido de la respuesta dependiendo la solicitud que esta gestionando
     */
    private Object data;
    /**
     * Genera una estructura de lista con lo error que se generan, con sus tipo de codigo y descripcion para facilitar la interpreteacion
     */
    private List<ErrorDTO> errors;
}
