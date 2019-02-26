/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import UtilityMessages.UTILMessage;

/**
 *
 * @author Asus
 */
public class UtilPropagationPhase {
    public Node graph[];
    int nodeCnt = Constants.nodeCnt;
    public UTILMessage utilMessage = new UTILMessage();

    public UtilPropagationPhase(Node[] graph) {
        this.graph = graph;
    }
    
    public UTILMessage recursiveHelper(int node) {
        UTILMessage ret = null;
        
        if(graph[node].child.size() == 0) {
            for(Integer temp: graph[node].domain) {
                ret = new UTILMessage();
                ret.update(node, Integer.valueOf((int) temp));
            }
        }
        
        for(Node nod: graph[node].child) {
           ret = recursiveHelper(nod.id);
           for(Integer temp: graph[node].domain) {
                ret = new UTILMessage();
                ret.update(node, Integer.valueOf((int) temp));
            }
        }
        
        return ret;
    }
    
    public void executeUtilPropagation() {
        utilMessage = recursiveHelper(Constants.root);
    }

}
