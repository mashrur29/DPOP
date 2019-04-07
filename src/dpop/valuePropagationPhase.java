/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import UtilityMessages.Assignments;

/**
 *
 * @author Asus
 */
public class valuePropagationPhase {

    public Node graph[];
    Assignments optimalAssignment;
    
    public valuePropagationPhase(Node[] graph, Assignments optimalAssignment) {
        this.graph = graph;
        this.optimalAssignment = optimalAssignment;
    }
    
    public void recursiveHelper(int node) {
        int maxi = Constants.min_int;
        
        for(int i = 0; i<graph[node].receivedUtils.length; i++) {
            if(graph[node].receivedUtils[i] > maxi) {
                maxi = graph[node].receivedUtils[i];
                graph[node].valueAssigned = i;
            }
        }
        
        for (Node nod : graph[node].child) {
            recursiveHelper(nod.id);
        }
        
    }
    
    public void executeValuePropagationPhase() {
        recursiveHelper(Constants.root);
    }
    
}
