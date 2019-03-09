/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import UtilityMessages.Assignments;
import UtilityMessages.UTILMessage;
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
    }

    public void recursiveHelper(Set<Integer> leaf_set) {
        boolean revised = false;
        Set<Integer> leaf_set_temp = new HashSet<Integer>();
        
        System.out.println("Current leaf set status");
        for(Integer nodeInt : leaf_set) {
            int node = Integer.valueOf((int) nodeInt);
            System.out.print(node + " ");
        }
        System.out.println("");
        
        for (Integer nodeInt : leaf_set) {
            int node = Integer.valueOf((int) nodeInt);
            
            if(node == Constants.root) {
                continue;
            }
            System.out.println("lol " + node);
            node = graph[node].parent.id;
            leaf_set_temp.add(new Integer(node));
        }
        

        for (Integer nodeInt : leaf_set_temp) {
            int node = Integer.valueOf((int) nodeInt);
            if (node != 1) {
                revised = true;
            }

            for (Integer temp : graph[node].domain) {
                System.out.println("node: " + node + " val: " + Integer.valueOf((int) temp));
                final_.update(node, Integer.valueOf((int) temp));
            }

        }
        
        System.out.println("done");

        if (!revised) {
            return;
        } else {
            recursiveHelper(leaf_set_temp);
            return;
        }

    }

    public void executeUtilPropagation() {
        Set<Integer> leaf_set = new HashSet<Integer>();

        for (int i = 1; i <= Constants.nodeCnt; i++) {
            if (graph[i].child.size() == 0) {
                leaf_set.add(new Integer(i));
            }
        }

        boolean firstAssignment = true;

        for (Integer nodeInt : leaf_set) {
            int node = Integer.valueOf((int) nodeInt);

            for (Integer temp : graph[node].domain) {
                int val = Integer.valueOf((int) temp);
                Assignments newAss = new Assignments();
                newAss.assignedValues[node] = val;
                
                if (firstAssignment) {
                    final_.assign.add(newAss);
                } else {
                    final_.update(node, val);
                }
            }
            firstAssignment = false;
        }

//        System.out.println("-----------------------------");
//        for (Assignments temp : final_.assign) {
//            System.out.println("Cost: " + temp.cost);
//            for (int i = 1; i <= Constants.nodeCnt; i++) {
//                System.out.println(i + " : " + temp.assignedValues[i]);
//            }
//        }
//
//        System.out.println("-------------------------");

        recursiveHelper(leaf_set);
    }

}
