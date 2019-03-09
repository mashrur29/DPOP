/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Satisfiability;

import dpop.Constants;
import java.util.Arrays;

/**
 *
 * @author mashrur
 */
public class Constraints {

    public static int constraints[][][][] = new int[Constants.maxAgents][Constants.maxAgents][Constants.maxAgents][Constants.maxAgents];
    public static int isHard[][] = new int[Constants.maxAgents][Constants.maxAgents];
    public static int vrm[][][][] = new int[Constants.maxAgents][Constants.maxAgents][Constants.maxAgents][Constants.maxAgents];
    
    public static void initArray() {
        for (int[][][] row : Constraints.constraints) {
            for (int[][] rowColumn : row) {
                for (int[] rowCol : rowColumn) {
                    Arrays.fill(rowCol, Constants.max_int);
                }
            }
        }
        
        for (int[][][] row : Constraints.vrm) {
            for (int[][] rowColumn : row) {
                for (int[] rowCol : rowColumn) {
                    Arrays.fill(rowCol, 0);
                }
            }
        }
        
        for(int[] row: isHard) {
            Arrays.fill(row, -1);
        }
    }
    
    public static boolean satisfies(int u, int v, int type) {
        if (type == 1) {
            return u <= v;
        }
        return true;
    }

}
