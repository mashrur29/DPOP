/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Satisfiability;

import dpop.Constants;

/**
 *
 * @author mashrur
 */
public class Constraints {

    public static int constraints[][][][] = new int[Constants.maxAgents][Constants.maxAgents][Constants.maxAgents][Constants.maxAgents];

    public static boolean satisfies(int u, int v, int type) {
        if (type == 1) {
            return u <= v;
        }
        return true;
    }

}
