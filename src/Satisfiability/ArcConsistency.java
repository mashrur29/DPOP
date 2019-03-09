/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Satisfiability;

import dpop.Constants;
import dpop.Node;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.util.Pair;

/**
 *
 * @author mashrur
 *
 * This Class implements the AC-3 algorithm
 */
public class ArcConsistency {

    public Node[] graph;
    Queue<Pair<Integer, Integer>> q = new LinkedList<>();

    public ArcConsistency(Node[] graph) {
        this.graph = graph;
    }

    public boolean revise(int u, int v, int type) {
        boolean revised = false;
        List<Integer> deleteList = new LinkedList<Integer>();
        
        for(Integer i: graph[u].domain) {
            boolean atleastone = false;
            for(Integer j: graph[v].domain) {
                if(Constraints.satisfies(i, j, type) == true) {
                    atleastone = true;
                }
            }
            if(atleastone == false) {
                deleteList.add(i);
                revised = true;
            }
        }
        
        if(deleteList.size() > 0) {
            for(Integer i: deleteList) {
                graph[u].domain.remove(i);
            }
        }
        
        return revised;
    }

    public boolean AC3() {
        for (int i = 1; i <= Constants.nodeCnt; i++) {
            for (Node node : graph[i].neighbors) {
                if (Constraints.isHard[i][node.id] != -1) {
                    q.add(new Pair(i, node.id));
                }
            }
        }

        while (q.isEmpty() == false) {
            Pair top = q.remove();
            int u = (int) top.getKey();
            int v = (int) top.getValue();
            if (graph[u].domain.isEmpty()) {
                return false;
            }

            if (revise(u, v, Constraints.isHard[u][v]) == true) {
                if(graph[u].domain.isEmpty()) return false;
                
                for (Node node : graph[u].neighbors) {
                    if (node.id == v) {
                        continue;
                    }
                    if (Constraints.isHard[u][node.id] == -1) {
                        continue;
                    }
                    q.add(new Pair(u, node.id));
                }
            }

        }

        return true;
    }
}
