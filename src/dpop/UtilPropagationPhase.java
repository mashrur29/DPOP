/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import Satisfiability.Constraints;
import UtilityMessages.Assignments;
import UtilityMessages.UTILMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class UtilPropagationPhase {

    public Node graph[];
    int nodeCnt = Constants.nodeCnt;
    public UTILMessage final_ = new UTILMessage();
    public List<Node> leaf = new LinkedList<Node>();
    public List<UTILMessage> messages = new LinkedList<UTILMessage>();

    public UtilPropagationPhase(Node[] graph) {
        this.graph = graph;
        leaf = new LinkedList<Node>();
    }

    public void recursiveHelper(Set<Integer> leaf_set) {
        Set<Integer> delete_set = new HashSet<Integer>();
        //System.out.println(leaf_set.size());
        Set<Integer> leaf_set_iterator = new HashSet<Integer>(leaf_set);
        int sendUtil[] = new int[Constants.domainEnd + 1];
        Arrays.fill(sendUtil, Constants.max_int);
        
        for (Integer curLeaf : leaf_set_iterator) {

            int curLeafVal = curLeaf;

            if (curLeafVal == Constants.root) {
                return;
            }
            
            if(graph[curLeaf].parent == null) {
                continue;
            }
            
            int par = graph[curLeafVal].parent.id;

            if (graph[curLeafVal].numAckChild != graph[curLeafVal].child.size()) {
                continue;
            } else {
                delete_set.add(curLeaf);
                if (graph[curLeafVal].locallyReducible == false) {
                    for (int i = Constants.domainStart; i <= Constants.domainEnd; i++) {
                        graph[par].receivedUtils[i] += sendUtil[i];
                    }
//                    for (Node node : graph[curLeafVal].pseudoNeighbor) {
//                        for (Integer i : graph[node.id].domain) {
//                            graph[node.id].receivedUtils[i] += sendUtil[i];
//                        }
//                    }

                    continue;
                }
            }
            
            
//            System.out.println(curLeafVal + " " + par);
//            for(Integer i1 : graph[par].domain) {
//                for(Integer j1 : graph[curLeafVal].domain) {
//                    System.out.print(Constraints.constraints[curLeafVal][par][j1][i1] + " ");
//                }
//                System.out.println("");
//            }

            // projection and join operation
            for (Integer i : graph[par].domain) {
                int mini = Constants.max_int;
                int id = 0;
                for (Integer j : graph[curLeafVal].domain) {
                    if(Constraints.constraints[curLeafVal][par][j][i] < mini) {
                        mini = Constraints.constraints[curLeafVal][par][j][i];
                        id = j;
                    }
                }
                mini += graph[curLeafVal].receivedUtils[i];
                sendUtil[i] = mini;
            }

            for (int i = Constants.domainStart; i <= Constants.domainEnd; i++) {
                graph[par].receivedUtils[i] += sendUtil[i];
            }

            graph[par].numAckChild++;
            leaf_set.add(par);

            for (Node node : graph[curLeafVal].pseudoNeighbor) {
                for (Integer i : graph[node.id].domain) {
                    graph[node.id].receivedUtils[i] += sendUtil[i];
                }
            }

        }

        for (Integer deleteLeaf : delete_set) {
            leaf_set.remove(deleteLeaf);
        }

        recursiveHelper(leaf_set);
    }

    public void executeUtilPropagation() {
        Set<Integer> leaf_set = new HashSet<Integer>();

        for (int i = 1; i <= Constants.nodeCnt; i++) {
            if (graph[i].child.size() == 0) {
                leaf_set.add(new Integer(i));
            }
        }
        
        
        recursiveHelper(leaf_set);
        
        System.out.println("Received Utils");
        for(int i = 1; i<=Constants.nodeCnt; i++) {
            System.out.print(i + ": ");
            for(int j = Constants.domainStart; j<=Constants.domainEnd; j++) {
                System.out.print("" + graph[i].receivedUtils[j] + "  ");
            }
            System.out.println("");
        }
        leaf_set.clear();
    }

}
