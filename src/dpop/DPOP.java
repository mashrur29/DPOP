/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import PseudoTreeConstruction.LayerMessage;
import UtilityMessages.Assignments;
import clusterRemoving.nonDistributedClusterRemoving;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class DPOP {
    public static Messages msg = new Messages();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        
        BFSDPOP bfsDpop = new BFSDPOP();
        bfsDpop.executeBfsDpop();
        
        Instant finish = Instant.now();
        double timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Exeecution Time: " + timeElapsed + "ms");
    }
    
}
