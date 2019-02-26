/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilityMessages;

import dpop.Constants;
import dpop.Node;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Assignments {
    public int cost = 0;
    public int assignedValues[] = new int[Constants.nodeCnt+1];

    public Assignments() {
        for(int i=0; i<=Constants.nodeCnt; i++) {
            assignedValues[i] = -1;
        }
    }
    
    public Assignments(int cost, int assignedValues[]) {
        this.cost = cost;
        this.assignedValues = assignedValues;
    }
    
    public Assignments deepcopy() {
        return new Assignments(cost, assignedValues);
    }
    
}
