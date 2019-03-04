/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PythonProcedure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Asus
 */
public class ExecutePython {

    public static void callPythonScript() {
        try {
            String pathFile = "/home/mashrur/Dropbox/Thesis/DPOP/DPOP/src/PythonProcedure/run.py";
            Process p = Runtime.getRuntime().exec("python " + pathFile);
        } catch (IOException e) {
        }
    }

}
