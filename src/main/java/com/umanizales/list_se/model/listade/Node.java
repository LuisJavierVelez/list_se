package com.umanizales.list_se.model.listade;

import com.umanizales.list_se.model.Boy;
import lombok.Data;

@Data
public class Node {
    private Boy data;
    private Node next;
    private Node previous;

    public Node(Boy data) {
        this.data = data;
    }

    public void getPrevious(Node temp) {
    }
}
