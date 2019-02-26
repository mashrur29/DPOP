/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import UtilityMessages.Assignments;
import UtilityMessages.UTILMessage;
import com.sun.org.apache.bcel.internal.classfile.Constant;

/**
 *
 * @author Asus
 */
public class BFSDPOP {
    public Node graph[];

    public BFSDPOP(Node[] graph) {
        this.graph = graph;
    }
    
    public void executeBfsDpop() {
        UtilPropagationPhase utilphase = new UtilPropagationPhase(graph);
        utilphase.executeUtilPropagation();
        
        System.out.println("Util Propagation Phase Complete ");
        System.out.println("The message constains");
        for(Assignments temp: utilphase.utilMessage.assign) {
            System.out.println("Cost: " + temp.cost);
            for(int i=1; i<=Constants.nodeCnt; i++) {
                System.out.println(i + " : " + temp.assignedValues[i]);
            }
        }
        
    }
    
}
