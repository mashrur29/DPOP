/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import PseudoTreeConstruction.AckMessage;
import PseudoTreeConstruction.LayerMessage;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.python.bouncycastle.util.Arrays;

/**
 *
 * @author Asus
 */
public class Node extends Thread {

    public Node parent;
    public List<Node> child = new LinkedList<Node>();
    public List<Node> pseudoNeighbor = new LinkedList<Node>();
    public int level;
    public List<Node> neighbors = new LinkedList<Node>();
    public int neighborCount;
    public int id;
    public List<Integer> domain = new LinkedList<Integer>();
    public boolean locallyReducible = true;
    public int valueAssigned = Constants.restricted;
    public int receivedUtils[];
    public int pseudoNeighborSize;
    public int numAckChild;

    public Node(int id) {
        parent = null;
        level = Constants.max_int;
        neighborCount = 0;
        this.id = id;
        numAckChild = 0;
        receivedUtils = new int[Constants.domainEnd+1];
        Arrays.fill(receivedUtils, 0);
    }

    public Node(Node parent, List child, List pseudoNeighbor, int level, List neighbors, int neighborCount, int id, List<Integer> domain, boolean locallyReducible, int valueAssigned, int receivedUtils[], int pseudoNeighborSize, int numAckChild) {
        this.parent = parent;
        this.child = child;
        this.pseudoNeighbor = pseudoNeighbor;
        this.level = level;
        this.neighbors = neighbors;
        this.neighborCount = neighborCount;
        this.id = id;
        this.domain = domain;
        this.locallyReducible = locallyReducible;
        this.valueAssigned = valueAssigned;
        this.receivedUtils = receivedUtils;
        this.pseudoNeighborSize = pseudoNeighborSize;
        this.numAckChild = numAckChild;
    }
    
    public Node deepcopy() {
        return new Node(parent, child, pseudoNeighbor, level, neighbors, neighborCount, id, domain, locallyReducible, valueAssigned, receivedUtils, pseudoNeighborSize, numAckChild);
    }

    void addNeighbor(Node node) {
        neighbors.add(node);
        neighborCount++;
    }

    public int findChild(int id) {
        for (int i = 0; i < child.size(); i++) {
            if (child.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void run() {
        int recNeighbor = 0;
        while (DPOP.msg.ackMessage[id].size() < neighborCount) {
            
            //System.out.println("Currently in node: " + id + " ack's received: " + DPOP.msg.ackMessage[id].size());
            LayerMessage layermsg = DPOP.msg.getLayerMessage(id);
            Map<Integer, Integer> childAdded = new HashMap<>();
            if(layermsg!=null) System.out.println(id + ": " + DPOP.msg.ackMessage[id].size() + " sender: " + layermsg.sender.id);
            
            if (layermsg != null) {
                if (layermsg.sender.level + 1 < level) {
                    System.out.println(id + ": inside: " + layermsg.sender.id);
                    level = layermsg.sender.level + 1;
                    parent = layermsg.sender.deepcopy();
                    for (int i = 0; i < neighborCount; i++) {
                        Node tmp = neighbors.get(i).deepcopy();
                        if (tmp.id != id && tmp.id != layermsg.sender.id && !(childAdded.containsKey(new Integer(tmp.id)))) {
                            System.out.println(id + " making child " + tmp.id);
                            child.add(tmp);
                            childAdded.put(new Integer(tmp.id), new Integer(1));
                        }
                    }
                    neighborCount = child.size();

                    for (Node node : child) {
                        //if(messageSent[node.id].containsKey(new Integer(id))) continue;
                        System.out.println(id + ": " + "sending layer message to " + node.id);
                        DPOP.msg.addLayerMessage(node.id, new LayerMessage(this.deepcopy()));
                        //messageSent[node.id].put(new Integer(id), new Integer(1));
                    }
                    
                    
                    //Node.acks[layermsg.sender.id]++;
                    
                }
                else {
                    int index = findChild(layermsg.sender.id);
                    if(index != -1) {
                        System.out.println("Child" + child.get(index).id + " " + id);
                        child.remove(index);
                        pseudoNeighbor.add(layermsg.sender.deepcopy());
                    }
                }
                System.out.println(id + ": " + "sending ack message to " + layermsg.sender.id);
                DPOP.msg.addAckMessage(layermsg.sender.id, new AckMessage(this.deepcopy()));
                layermsg = null;
            }

        }
        
        System.out.println("Node " + id + " Terminated");
    }

}
