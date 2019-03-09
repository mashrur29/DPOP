/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilityMessages;

import dpop.Constants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class UTILMessage {

    public Set<Assignments> assign = new HashSet<Assignments>();
    public int receiverId = Constants.restricted;
    public int done_[][] = new int[Constants.nodeCnt + 1][Constants.nodeCnt + 1];
    public List<Integer> valuesPresent = new LinkedList<>();
    
    public void update(int node, int val) {
        Set<Assignments> assignTemp = new HashSet<Assignments>();

        for (Assignments temp : assign) {
            if (temp.assignedValues[node] == Constants.restricted) {
                temp.addAssignment(node, val);
            } 
            else {
                Assignments tempNew = new Assignments();
                tempNew.cost = temp.cost;
                tempNew.assignedValues = Arrays.copyOf(temp.assignedValues, temp.assignedValues.length);
                
                tempNew.addAssignment(node, val);
                assignTemp.add(tempNew);
            }
        }

        for (Assignments addAssignment : assignTemp) {
            assign.add(addAssignment);
        }
    }

}
