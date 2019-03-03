/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PythonProcedure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 *
 * @author Asus
 */
public class ExecutePython {

    public static void callPythonScript() {
        try {

            String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
            BufferedWriter out = new BufferedWriter(new FileWriter("run.py"));
            out.write(prg);
            out.close();
            int number1 = 10;
            int number2 = 32;
            String pathFile = "C:\\Users\\Asus\\Dropbox\\Thesis\\DPOP\\DPOP\\src\\PythonProcedure\\run.py";
            Process p = Runtime.getRuntime().exec("python " + pathFile + " " + number1 + " " + number2);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int ret = new Integer(in.readLine()).intValue();
            System.out.println("value is : " + ret);
        } catch (Exception e) {
        }
    }

}
