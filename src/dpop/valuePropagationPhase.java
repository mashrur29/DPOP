/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import Satisfiability.Constraints;
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

    public void recursiveHelper(int node, int parent, int sum) {
        int mini = 0;
        if (node == Constants.root) {
            for (Node nod : graph[node].child) {
                recursiveHelper(nod.id, node, sum);
            }
        }
        else {
            
            for (Integer i : graph[parent].domain) {
                int done = 0;
                
                for (Integer j : graph[node].domain) {
                    if(Constraints.constraints[node][parent][j][i] + graph[node].receivedUtils[i] == sum) {
                        mini = graph[node].receivedUtils[i];
                        graph[node].valueAssigned = i;
                        done = 1;
                        break;
                    }
                }
                
                if(done == 1) break;
                
            }
            
        }


        for (Node nod : graph[node].child) {
            recursiveHelper(nod.id, node, mini);
        }

    }

    public void executeValuePropagationPhase() {
        int mini = Constants.max_int;
        int id = 0;

        for (Integer i : graph[Constants.root].domain) {
            int dd = i;
            if (graph[Constants.root].receivedUtils[dd] < mini) {
                mini = graph[Constants.root].receivedUtils[dd];
                id = dd;
            }
        }
        
        graph[Constants.root].valueAssigned = id;
        recursiveHelper(Constants.root, Constants.root, mini);
        
        System.out.println("Values Assigned:");
        for(int i=1; i<=Constants.nodeCnt; i++) {
            System.out.print(graph[i].valueAssigned + " ");
        }
        System.out.println("");
    }

}
