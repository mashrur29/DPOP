/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clusterRemoving;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import dpop.Constants;
import dpop.Node;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Asus
 */
public class nonDistributedClusterRemoving {

    public Node graph[];
    public int cecSize[];
    public int nodeCnt;

    public nonDistributedClusterRemoving(Node[] graph) {
        this.graph = graph;
        nodeCnt = graph.length - 1;
        cecSize = new int[nodeCnt + 1];
    }

    public void precompute() {
        for (int i = 1; i <= nodeCnt; i++) {
            cecSize[i] = graph[i].pseudoNeighbor.size();
        }
    }

    public Pair<Integer, Integer> removeClusterHelper(int node) {
        Pair ret;
        int maxSiz = graph[node].pseudoNeighbor.size(), maxId = node;
        
        if (graph[node].child.size() == 0) {
            return (new Pair(new Integer(node), new Integer(graph[node].pseudoNeighbor.size())));
        }
        
        for (Node nod : graph[node].child) {
            int id = nod.id;
            int clusterSize = nod.pseudoNeighbor.size();
            
            if(maxSiz < clusterSize) {
                maxSiz = clusterSize;
                maxId = id;
            }
            
            Pair tmp = removeClusterHelper(id);
            if(maxSiz < Integer.valueOf((int) tmp.getValue())) {
                maxSiz = Integer.valueOf((int) tmp.getValue());
                maxId = Integer.valueOf((int) tmp.getKey());
            }
        }
        
        return new Pair(new Integer(maxId), new Integer(maxSiz));
    }
    
    public void allocate(int node, int maxId) {
        if(node == maxId) {
            for(Node nod: graph[node].pseudoNeighbor) {
                 int otherId = nod.id;
                 graph[otherId].child.add(graph[node].deepcopy());
                 graph[node].parent = nod.deepcopy();
                 for(Node nod1: graph[otherId].pseudoNeighbor) {
                     if(nod1.id == maxId) {
                         graph[otherId].pseudoNeighbor.remove(nod1);
                         break;
                     }
                 }
            }
            graph[node].pseudoNeighbor.clear();
        }
        
        for(Node nod: graph[node].child) {
            allocate(nod.id, maxId);
        }
        
    }
    
    public void removeCluster() {
        precompute();
        
        while(true) {
            Pair tmp = removeClusterHelper(Constants.root);
            int maxSiz = Integer.valueOf((int) tmp.getValue());
            int maxId = Integer.valueOf((int) tmp.getKey());
            
            if(maxSiz == 0) {
                break;
            }
            else {
                allocate(Constants.root, maxId);
            }
        }
        
        System.out.println("After Cluster Removing:");
        
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
