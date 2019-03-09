/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import PseudoTreeConstruction.LayerMessage;
import PythonProcedure.ExecutePython;
import UtilityMessages.Assignments;
import clusterRemoving.nonDistributedClusterRemoving;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Asus
 * Mashrur Rashik
 * Written for research purpose
 * 
 * This is a minimization problem
 * 
 */
public class DPOP {
    
    public static Messages msg = new Messages();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        //ExecutePython.callPythonScript();
        
        DFSDPOP dfsdpop = new DFSDPOP();
        dfsdpop.executeDfsDpop();
        
        BFSDPOP bfsDpop = new BFSDPOP();
        bfsDpop.executeBfsDpop();
        
        BrcBFSDPOP brcbfsdpop = new BrcBFSDPOP();
        brcbfsdpop.executeBfsDpop();
        
        System.out.println("Execution Time for DFS DPOP: " + dfsdpop.timElapsed + "ms");
        System.out.println("Execution Time for BFS DPOP: " + bfsDpop.timElapsed + "ms");
        System.out.println("Execution Time for Brc BFS DPOP: " + brcbfsdpop.timElapsed + "ms");
        
        String str = String.valueOf(Constants.nodeCnt) + ", " + String.valueOf(dfsdpop.timElapsed) + ", " + String.valueOf(bfsDpop.timElapsed) + ", " + String.valueOf(brcbfsdpop.timElapsed) + "\n";
        String fileName = "timeOutput.csv";
        //FileIO.FileAppend.appendStrToFile(fileName, str);
    }
    
}
