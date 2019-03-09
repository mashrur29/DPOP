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
import org.python.util.PythonInterpreter;

/**
 *
 * @author Asus
 */
public class ExecutePython {

    public static void callPythonScript() throws IOException {
        try {
            Process p = Runtime.getRuntime().exec("python run.py");
            System.out.println("done");
        } catch (Exception e) {
            System.out.println("Exception");
        }
        
    }

}
