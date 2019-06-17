/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import FileIO.FileCopy;
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
 * @author Asus Mashrur Rashik Written for research purpose
 *
 * This is a minimization problem
 *
 */
public class DPOP {

    public static Messages msg = new Messages();

    /**
     * @param args the command line arguments
     */
    public static void executeSimultion() throws InterruptedException, IOException {
        DFSDPOP dfsdpop = new DFSDPOP();
        dfsdpop.executeDfsDpop();

        BFSDPOP bfsDpop = new BFSDPOP();
        bfsDpop.executeBfsDpop();

        CecDPOP cecdpop = new CecDPOP();
        cecdpop.executeCecDpop();
        
        BrcDPOP brcdpop = new BrcDPOP();
        brcdpop.executeBrcDpop();

        System.out.println("Execution Time for DFS DPOP: " + dfsdpop.timElapsed + "ms");
        System.out.println("Execution Time for BFS DPOP: " + bfsDpop.timElapsed + "ms");
        System.out.println("Execution Time for BRC DPOP: " + brcdpop.timElapsed + "ms");
        System.out.println("Execution Time for Cec DPOP: " + cecdpop.timElapsed + "ms");

        String str = String.valueOf(Constants.nodeCnt) + "," + String.valueOf(dfsdpop.timElapsed) + "," + String.valueOf(bfsDpop.timElapsed) + "," + String.valueOf(brcdpop.timElapsed) + "," + String.valueOf(cecdpop.timElapsed) + "\n";
        String fileName = "timeOutput.csv";
        FileIO.FileAppend.appendStrToFile(fileName, str);
    }

    public static void nodeTimeSimulation() throws IOException, InterruptedException {
        for (int sim = 1; sim <= 6; sim++) {
            String src = "/home/mashrur/Dropbox/Thesis/DPOP/DPOP/simulations/simulation";
            src += Integer.toString(sim);
            src += ".txt";
            System.out.println(src);
            FileCopy.fileToFileCopy(src, "inputRandomGraph.txt");

            double timeBfs = 0.0, timeDfs = 0.0, timeCec = 0.0, timeBrc = 0.0;
            int nodeCnt = 0, numTest = 100;

            for (int cnt = 1; cnt <= numTest; cnt++) {
                DFSDPOP dfsdpop = new DFSDPOP();
                dfsdpop.executeDfsDpop();
                nodeCnt = Constants.nodeCnt;

                CecDPOP cecdpop = new CecDPOP();
                cecdpop.executeCecDpop();

                BFSDPOP bfsDpop = new BFSDPOP();
                bfsDpop.executeBfsDpop();
                
                BrcDPOP brcdpop = new BrcDPOP();
                brcdpop.executeBrcDpop();

                timeDfs += dfsdpop.timElapsed;
                timeBfs += bfsDpop.timElapsed;
                timeCec += cecdpop.timElapsed;
                timeBrc += brcdpop.timElapsed;
            }

            timeBfs /= (double)numTest;
            timeDfs /= (double)numTest;
            timeCec /= (double)numTest;
            timeBrc /= (double)numTest;

            String str = String.valueOf(nodeCnt) + "," + String.valueOf(timeDfs) + "," + String.valueOf(timeBfs) + "," + String.valueOf(timeBrc) + "," + String.valueOf(timeCec) + "\n";
            String fileName = "timeOutput.csv";
            FileIO.FileAppend.appendStrToFile(fileName, str);
        }
    }
    
    public static void domainTimeSimulation() throws IOException, InterruptedException {
        for (int sim = 1; sim <= 6; sim++) {
            String src = "/home/mashrur/Dropbox/Thesis/DPOP/DPOP/simulations/VariableDomain/simulation";
            src += Integer.toString(sim);
            src += ".txt";
            System.out.println(src);
            FileCopy.fileToFileCopy(src, "inputRandomGraph.txt");

            double timeBfs = 0.0, timeDfs = 0.0, timeCec = 0.0, timeBrc= 0.0;
            int domainCnt = 0, numTest = 100;

            for (int cnt = 1; cnt <= numTest; cnt++) {
                DFSDPOP dfsdpop = new DFSDPOP();
                dfsdpop.executeDfsDpop();
                domainCnt = Constants.domainSize;

                CecDPOP cecdpop = new CecDPOP();
                cecdpop.executeCecDpop();

                BFSDPOP bfsDpop = new BFSDPOP();
                bfsDpop.executeBfsDpop();
                
                BrcDPOP brcdpop = new BrcDPOP();
                brcdpop.executeBrcDpop();

                timeDfs += dfsdpop.timElapsed;
                timeBfs += bfsDpop.timElapsed;
                timeCec += cecdpop.timElapsed;
                timeBrc += brcdpop.timElapsed;
            }

            timeBfs /= (double)numTest;
            timeDfs /= (double)numTest;
            timeCec /= (double)numTest;
            timeBrc /= (double)numTest;

            String str = String.valueOf(domainCnt) + "," + String.valueOf(timeDfs) + "," + String.valueOf(timeBfs) + "," + String.valueOf(timeBrc) + "," + String.valueOf(timeCec) + "\n";
            String fileName = "timeOutput.csv";
            FileIO.FileAppend.appendStrToFile(fileName, str);
        }
    }
    
    public static void densityTimeSimulation() throws IOException, InterruptedException {
        for (int sim = 1; sim <= 5; sim++) {
            String src = "/home/mashrur/Dropbox/Thesis/DPOP/DPOP/simulations/VariableDensity/simulation";
            src += Integer.toString(sim);
            src += ".txt";
            System.out.println(src);
            FileCopy.fileToFileCopy(src, "inputRandomGraph.txt");

            double timeBfs = 0.0, timeDfs = 0.0, timeCec = 0.0, timeBrc = 0.0;
            double density = 0;
            int numTest = 100;

            for (int cnt = 1; cnt <= numTest; cnt++) {
                DFSDPOP dfsdpop = new DFSDPOP();
                dfsdpop.executeDfsDpop();
                density = ((2.0 * (double)Constants.edgeCnt) / ((double)Constants.nodeCnt * (double)(Constants.nodeCnt - 1)));

                CecDPOP cecdpop = new CecDPOP();
                cecdpop.executeCecDpop();

                BFSDPOP bfsDpop = new BFSDPOP();
                bfsDpop.executeBfsDpop();
                
                BrcDPOP brcdpop = new BrcDPOP();
                brcdpop.executeBrcDpop();

                timeDfs += dfsdpop.timElapsed;
                timeBfs += bfsDpop.timElapsed;
                timeCec += cecdpop.timElapsed;
                timeBrc += brcdpop.timElapsed;
            }

            timeBfs /= (double)numTest;
            timeDfs /= (double)numTest;
            timeCec /= (double)numTest;
            timeBrc /= (double)numTest;

            String str = String.valueOf(density) + "," + String.valueOf(timeDfs) + "," + String.valueOf(timeBfs) + "," + String.valueOf(timeBrc) + "," + String.valueOf(timeCec) + "\n";
            String fileName = "timeOutput.csv";
            FileIO.FileAppend.appendStrToFile(fileName, str);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        //ExecutePython.callPythonScript();
        //FileCopy.fileToFileCopy("/home/mashrur/Dropbox/Thesis/DPOP/DPOP/simulations/simulation1.txt", "inputRandomGraph");
        testSimulation();
        //nodeTimeSimulation();
        //densityTimeSimulation();
        //domainTimeSimulation();
        System.out.println("Complete");
    }

    public static void testSimulation() throws InterruptedException, IOException {
        System.out.println("This is a test simulation");
        DFSDPOP dfsdpop = new DFSDPOP();
        dfsdpop.executeDfsDpop();

        BFSDPOP bfsDpop = new BFSDPOP();
        bfsDpop.executeBfsDpop();

        CecDPOP cecdpop = new CecDPOP();
        cecdpop.executeCecDpop();
        
        BrcDPOP brcdpop = new BrcDPOP();
        brcdpop.executeBrcDpop();

        System.out.println("Execution Time for DFS DPOP: " + dfsdpop.timElapsed + "ms");
        System.out.println("Execution Time for BFS DPOP: " + bfsDpop.timElapsed + "ms");
        System.out.println("Execution Time for BRC DPOP: " + brcdpop.timElapsed + "ms");
        System.out.println("Execution Time for Cec DPOP: " + cecdpop.timElapsed + "ms");

        String str = String.valueOf(Constants.nodeCnt) + ", " + String.valueOf(dfsdpop.timElapsed) + ", " + String.valueOf(bfsDpop.timElapsed) + ", " + String.valueOf(brcdpop.timElapsed) + ", " + String.valueOf(cecdpop.timElapsed) + "\n";
    }

}
