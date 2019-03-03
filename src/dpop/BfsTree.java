/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import PseudoTreeConstruction.LayerMessage;

/**
 *
 * @author Asus
 */
public class BfsTree {
    public int root = 1, nodeCnt = Constants.nodeCnt;
    public Node graph[] = new Node[Constants.nodeCnt+1];
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    public static int constraints[][][][] = new int[Constants.maxAgents][Constants.maxAgents][Constants.maxAgents][Constants.maxAgents];
    
    void simulation1() {
        nodeCnt = Constants.nodeCnt;
        root = Constants.root;
        
        for(int i=1; i<=nodeCnt; i++) {    // 1--2
            graph[i] = new Node(i);        // | /   
        }                                  // 3
        
        graph[1].domain.add(new Integer(1));
        graph[1].domain.add(new Integer(2));
        graph[1].domain.add(new Integer(3));
        
        graph[2].domain.add(new Integer(1));
        graph[2].domain.add(new Integer(2));
        graph[2].domain.add(new Integer(3));
        
        graph[3].domain.add(new Integer(1));
        graph[3].domain.add(new Integer(2));
        graph[3].domain.add(new Integer(3));
        
        graph[1].addNeighbor(graph[2].deepcopy());
        graph[1].addNeighbor(graph[3].deepcopy());
        
        graph[2].addNeighbor(graph[1].deepcopy());
        graph[2].addNeighbor(graph[3].deepcopy());
        
        graph[3].addNeighbor(graph[1].deepcopy());
        graph[3].addNeighbor(graph[2].deepcopy());
        
        for(int i=Constants.domainStart; i<=Constants.domainEnd; i++) {
            for(int j=Constants.domainStart; j<=Constants.domainEnd; j++) {
                BfsTree.constraints[1][2][i][j] = 2;
                BfsTree.constraints[2][1][j][i] = 2;
            }
        }
        
        for(int i=Constants.domainStart; i<=Constants.domainEnd; i++) {
            for(int j=Constants.domainStart; j<=Constants.domainEnd; j++) {
                BfsTree.constraints[1][3][i][j] = 3;
                BfsTree.constraints[3][1][j][i] = 3;
            }
        }
        
        for(int i=Constants.domainStart; i<=Constants.domainEnd; i++) { // Hard Constraint
            for(int j=Constants.domainStart; j<=Constants.domainEnd; j++) {
                if(i < j) {
                    BfsTree.constraints[2][3][i][j] = 0;
                    BfsTree.constraints[3][2][j][i] = 0;
                }
                else {
                    BfsTree.constraints[2][3][i][j] = Constants.restricted;
                    BfsTree.constraints[3][2][j][i] = Constants.restricted;
                }
            }
        }
        
    }

    public void constructBfsTree() throws InterruptedException {
        simulation1();
        
        for(int i=1; i<=nodeCnt; i++) {
            if (i == root) {
                graph[i].level = 0;
                for(Node node: graph[i].neighbors) {
                    graph[i].child.add(node);
                }
                for(Node node: graph[i].child) {
                    System.out.println(node.id);
                    DPOP.msg.addLayerMessage(node.id, new LayerMessage(graph[i]));
                }
            }
        }
        
        for(int i=1; i<=nodeCnt; i++) {
            graph[i].start();
        }
        
        for(int i=1; i<=nodeCnt; i++) {
            graph[i].join();
        }
        
        System.out.println("Pseudo Tree Construction Complete");
        System.out.println("");
        System.out.println("The Generated Graph is:");
        
        for(int i=1; i<=nodeCnt; i++) {
            System.out.print("Node " + i + " child: ");
            if(graph[i].child.size() == 0) System.out.print("No Child");
            else {
                for(Node node: graph[i].child) {
                    System.out.print(node.id + " ");
                }
            }
            if(graph[i].parent != null) System.out.print(" , Parent: " + graph[i].parent.id);
            else System.out.print(" , It is root");
            System.out.println("");
        }
        System.out.println("");

        
    }
}
