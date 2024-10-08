/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import Satisfiability.ArcConsistency;
import UtilityMessages.Assignments;
import clusterRemoving.nonDistributedClusterRemoving;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author mashrur
 */
public class BrcDPOP {

    public Node graph[];
    public double timElapsed;

    public BrcDPOP() {
    }

    public BrcDPOP(Node[] graph) {
        this.graph = graph;
    }

    public void executeBrcDpop() throws InterruptedException, IOException {
        Instant start = Instant.now();
        DfsTree sim = new DfsTree();
        sim.constructDfsTree();

        ArcConsistency arcConsistent = new ArcConsistency(sim.graph);
        if (arcConsistent.AC3() == false) {
            System.out.println("Problem has no solution");
        } else {
            System.out.println("Arc Consistency Complete\n");
        }

        //nonDistributedClusterRemoving removeCluster = new nonDistributedClusterRemoving(sim.graph);
        //removeCluster.removeCluster();
        graph = sim.graph;

        System.out.println("");
        System.out.println("Util Propagation Starting");

        
        UtilPropagationPhase utilphase = new UtilPropagationPhase(graph);
        utilphase.executeUtilPropagation();

        System.out.println("Util Propagation Phase Complete ");

        int assignmentCnt = 0;
        int minCost = Constants.max_int;
        Assignments optimalAssignment = null;

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
