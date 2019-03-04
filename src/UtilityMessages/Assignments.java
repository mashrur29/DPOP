/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilityMessages;

import Satisfiability.Constraints;
import dpop.BfsTree;
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
    public int assignedValues[] = new int[Constants.nodeCnt + 1];

    public Assignments() {
        for (int i = 0; i <= Constants.nodeCnt; i++) {
            assignedValues[i] = Constants.restricted;
        }
    }

    public Assignments(int cost, int assignedValues[]) {
        this.cost = cost;
        this.assignedValues = assignedValues;
    }

    public void addAssignment(int node, int val) {
        assignedValues[node] = val;

        for (int i = 1; i <= Constants.nodeCnt; i++) {
            if (assignedValues[i] != Constants.restricted && i != node) {
                
                if (Constraints.constraints[node][i][val][assignedValues[i]] != Constants.restricted) {
                    if(Constraints.constraints[node][i][val][assignedValues[i]] != Constants.restricted) 
                        this.cost += Constraints.constraints[node][i][val][assignedValues[i]];
                }
            }
        }

    }

    public Assignments deepcopy() {
        return new Assignments(cost, assignedValues);
    }

}
