/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mashrur
 */
public class FileAppend {

    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
}
