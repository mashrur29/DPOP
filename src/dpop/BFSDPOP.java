/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import UtilityMessages.Assignments;
import UtilityMessages.UTILMessage;
import clusterRemoving.nonDistributedClusterRemoving;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author Asus
 */
public class BFSDPOP {

    public Node graph[];
    public double timElapsed;
    

    public BFSDPOP() {
    }
    
    public BFSDPOP(Node[] graph) {
        this.graph = graph;
    }

    public void executeBfsDpop() throws InterruptedException, IOException {
        BfsTree sim = new BfsTree();
        sim.constructBfsTree();
        nonDistributedClusterRemoving removeCluster = new nonDistributedClusterRemoving(sim.graph);
        removeCluster.removeCluster();
        graph = sim.graph;
        
        Instant start = Instant.now();
        System.out.println("");
        System.out.println("Util Propagation Starting");
        
        
        UtilPropagationPhase utilphase = new UtilPropagationPhase(graph);
        utilphase.executeUtilPropagation();
        
        System.out.println("Util Propagation Phase Complete ");
        
        for(int i=Constants.domainStart; i<=Constants.domainEnd; i++) {
            System.out.print(graph[Constants.root].receivedUtils[i] + " ");
        }
        System.out.println("");
        
        int assignmentCnt = 0;
        int minCost = Constants.max_int;
        Assignments optimalAssignment = null;

        for (Assignments temp : utilphase.final_.assign) {
            assignmentCnt++;
            
            if (temp.cost < minCost) {
                minCost = temp.cost;
                optimalAssignment = temp;
            }

        }
        
        
        valuePropagationPhase valuePropagate = new valuePropagationPhase(graph, optimalAssignment);
        
        System.out.println("Value Propagation Complete");
//        System.out.println("Optimal Assignment:");
//        System.out.println("Cost: " + optimalAssignment.cost);
//        for(int i=1; i<= Constants.nodeCnt; i++) {
//            System.out.println(i + " : " + optimalAssignment.assignedValues[i]);
//        }
//        
        Instant finish = Instant.now();
        timElapsed = Duration.between(start, finish).toMillis();
        
    }

}
