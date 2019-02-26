/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PseudoTreeConstruction;

import dpop.Node;

/**
 *
 * @author Asus
 */
public class LayerMessage {
    public Node sender;

    public LayerMessage(Node sender) {
        this.sender = sender;
    }
    
    public LayerMessage deepcopy() {
        return new LayerMessage(sender.deepcopy());
    }
    
}
