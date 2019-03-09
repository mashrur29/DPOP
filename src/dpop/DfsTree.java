/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import PseudoTreeConstruction.LayerMessage;
import Satisfiability.Constraints;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author mashrur
 */
public class DfsTree {

    public int visited[];
    public int root = 1, nodeCnt = Constants.nodeCnt;
    public Node graph[];
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);

    void simulation1() {
        graph = new Node[Constants.nodeCnt + 1];
        visited = new int[Constants.nodeCnt + 1];
        nodeCnt = Constants.nodeCnt;
        root = Constants.root;

        for (int i = 1; i <= nodeCnt; i++) {    // 1--2
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

        for (int i = Constants.domainStart; i <= Constants.domainEnd; i++) {
            for (int j = Constants.domainStart; j <= Constants.domainEnd; j++) {
                Constraints.constraints[1][2][i][j] = 2;
                Constraints.constraints[2][1][j][i] = 2;
            }
        }

        for (int i = Constants.domainStart; i <= Constants.domainEnd; i++) {
            for (int j = Constants.domainStart; j <= Constants.domainEnd; j++) {
                Constraints.constraints[1][3][i][j] = 3;
                Constraints.constraints[3][1][j][i] = 3;
            }
        }

        for (int i = Constants.domainStart; i <= Constants.domainEnd; i++) { // Hard Constraint
            for (int j = Constants.domainStart; j <= Constants.domainEnd; j++) {
                if (i < j) {
                    Constraints.constraints[2][3][i][j] = 0;
                    Constraints.constraints[3][2][j][i] = 0;
                } else {
                    Constraints.constraints[2][3][i][j] = Constants.restricted;
                    Constraints.constraints[3][2][j][i] = Constants.restricted;
                }
            }
        }

    }

    public void generateSimulation() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream("inputRandomGraph.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        strLine = br.readLine();
        String[] stringArrayTemp = strLine.split("\\s+");

        nodeCnt = Constants.nodeCnt = Integer.parseInt(stringArrayTemp[0]);
        Constants.maxAgents = nodeCnt;
        int edgeCnt = Integer.parseInt(stringArrayTemp[1]);
        root = Constants.root = Integer.parseInt(stringArrayTemp[2]);

        strLine = br.readLine();
        stringArrayTemp = strLine.split("\\s+");

        Constants.domainStart = Integer.parseInt(stringArrayTemp[0]);
        Constants.domainEnd = Integer.parseInt(stringArrayTemp[1]);

        graph = new Node[Constants.nodeCnt + 1];
        visited = new int[Constants.nodeCnt + 1];

        for (int i = 1; i <= nodeCnt; i++) {
            graph[i] = new Node(i);
        }

        for (int i = 1; i <= nodeCnt; i++) {
            for (int j = Constants.domainStart; j <= Constants.domainEnd; j++) {
                graph[i].domain.add(new Integer(j));
            }
        }

        //System.out.println("nodeCnt " + nodeCnt + " domainStart " + Constants.domainStart + " domainEnd " + Constants.domainEnd + " root " + Constants.root + " edgeCnt " + edgeCnt);
        while ((strLine = br.readLine()) != null) {
            //strLine = strLine.replaceAll("\\s+", "");

            String[] stringArray = strLine.split("\\s+");

            int u = Integer.parseInt(stringArray[0].trim());
            int v = Integer.parseInt(stringArray[1].trim());
            int type = Integer.parseInt(stringArray[2].trim());
            int cost = 0;

            graph[u].addNeighbor(graph[v].deepcopy());
            graph[v].addNeighbor(graph[u].deepcopy());
            

            if (type == 0) { // Hard Constraint
                for (int i = Constants.domainStart; i <= Constants.domainEnd; i++) {
                    strLine = br.readLine();
                    String[] stringArrayDomain = strLine.split("\\s+");

                    for (int j = Constants.domainStart; j <= Constants.domainEnd; j++) {
                        cost = Integer.parseInt(stringArrayDomain[j - 1]);
                        Constraints.isHard[u][v] = Constraints.isHard[v][u] = cost;
                        if (Constraints.satisfies(i, j, cost)) {
                            Constraints.constraints[u][v][i][j] = 0;
                            Constraints.constraints[v][u][j][i] = Constants.max_int;
                        } else {
                            Constraints.constraints[u][v][i][j] = Constants.max_int;
                            Constraints.constraints[v][u][j][i] = 0;
                        }
                    }
                }
            } else { // Soft Constraint
                for (int i = Constants.domainStart; i <= Constants.domainEnd; i++) {
                    strLine = br.readLine();
                    String[] stringArrayDomain = strLine.split("\\s+");

                    for (int j = Constants.domainStart; j <= Constants.domainEnd; j++) {
                        cost = Integer.parseInt(stringArrayDomain[j - 1]);
                        Constraints.constraints[u][v][i][j] = cost;
                        Constraints.constraints[v][u][j][i] = cost;
                    }
                }
            }

        }

    }

    public void recursiveDfs(int node, Node par) {
        if (par != null) {
            graph[node].parent = par.deepcopy();
            graph[par.id].child.add(graph[node].deepcopy());
        } else {
            graph[node].parent = null;
        }
        visited[node] = 1;

        for (Node nod : graph[node].neighbors) {
            if (visited[nod.id] == 0) {
                recursiveDfs(nod.id, graph[node].deepcopy());
            }
        }

    }

    public void constructDfsTree() throws InterruptedException, IOException {

        Constraints.initArray();

        //simulation1();
        generateSimulation();
        recursiveDfs(Constants.root, null);
        fixPseudoNeighbor();
        
        System.out.println("Pseudo Tree Construction Complete");
        System.out.println("");
        System.out.println("The Generated Graph is:");

        for (int i = 1; i <= nodeCnt; i++) {
            System.out.print("Node " + i + " child: ");
            if (graph[i].child.size() == 0) {
                System.out.print("No Child");
            } else {
                for (Node node : graph[i].child) {
                    System.out.print(node.id + " ");
                }
            }
            if (graph[i].parent != null) {
                System.out.print(" , Parent: " + graph[i].parent.id);
            } else {
                System.out.print(" , It is root");
            }
            System.out.println("");
        }
        System.out.println("");

    }

    private void fixPseudoNeighbor() {
        for (int i = 1; i <= Constants.nodeCnt; i++) {
            for (Node nod : graph[i].neighbors) {
                //System.out.println("neighbor: " + i + " " + nod.id);
                boolean ok1 = true, ok2 = true;
                if (graph[i].parent != null && graph[i].parent.id == nod.id) {
                    ok1 = false;
                }
                if (graph[i].child != null) {
                    for (Node nod1 : graph[i].child) {
                        if (nod1.id == nod.id) {
                            ok2 = false;
                            break;
                        }
                    }
                }
                if (ok1 && ok2) {
                    graph[i].pseudoNeighbor.add(nod);
                }
            }
            graph[i].pseudoNeighborSize = graph[i].pseudoNeighbor.size();
        }
    }
}
