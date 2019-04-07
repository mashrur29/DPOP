/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mashrur
 */
public class FileCopy {

    public static void fileToFileCopy(String src, String dst) throws FileNotFoundException, IOException {
        FileInputStream instream = null;
        FileOutputStream outstream = null;

        File infile = new File(src);
        File outfile = new File(dst);
        instream = new FileInputStream(infile);
        outstream = new FileOutputStream(outfile);
        byte[] buffer = new byte[1024];
        int length;
        /*copying the contents from input stream to
        * output stream using read and write methods
        */
        while ((length = instream.read(buffer)) > 0) {
            outstream.write(buffer, 0, length);
        }
        //Closing the input/output file streams
        instream.close();
        outstream.close();
        System.out.println("----------- Input File is Ready ---------");

    }
}
