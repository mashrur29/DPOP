/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilityMessages;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class UTILMessage {
    public List<Assignments> assign = new LinkedList<Assignments>();
    
    public void update(int node, int val) {
        for(Assignments temp: assign) {
            temp.addAssignment(node, val);
        }
    }
    
}
