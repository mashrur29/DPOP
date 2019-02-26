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
public class AckMessage {
    public Node sender;

    public AckMessage(Node sender) {
        this.sender = sender;
    }
    
    public AckMessage deepcopy() {
        return new AckMessage(sender.deepcopy());
    }
    
}
