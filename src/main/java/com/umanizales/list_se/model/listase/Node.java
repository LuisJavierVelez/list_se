package com.umanizales.list_se.model.listase;

import com.umanizales.list_se.model.Boy;
import lombok.Data;

@Data
public class Node {
    private Boy data;
    private Node next;

    /**
     * Constructor generado para crear un node para las lista simplemente enlazadas
     * el cual solicita el niño como entrada ya que no puedo tener un nodo vacio
     * No se inicializa el siguiente ya que cuando se crea un nodo el siguiente apunta a vacio o null
     * @param data en este parametros optenemos todos los datos del niño
     */

    public Node(Boy data){
        this.data = data;
    }
}
