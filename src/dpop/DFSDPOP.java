/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import UtilityMessages.Assignments;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author mashrur
 */
public class DFSDPOP {

    public Node graph[];
    public double timElapsed;

    public DFSDPOP() {
    }

    public DFSDPOP(Node[] graph) {
        this.graph = graph;
    }

    public void setGraph(Node[] graph) {
        this.graph = graph;
    }

    public void executeDfsDpop() throws InterruptedException, IOException {
        Instant start = Instant.now();
        DfsTree sim = new DfsTree();
        sim.constructDfsTree();
        this.setGraph(sim.graph);

        
        System.out.println("");
        System.out.println("Util Propagation Starting");

        UtilPropagationPhase utilphase = new UtilPropagationPhase(graph);
        utilphase.executeUtilPropagation();

        System.out.println("Util Propagation Phase Complete ");

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
        valuePropagate.executeValuePropagationPhase();
        
        System.out.println("Value Propagation Complete");

        Instant finish = Instant.now();
        timElapsed = Duration.between(start, finish).toMillis();

    }

}
