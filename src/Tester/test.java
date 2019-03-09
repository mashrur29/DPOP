/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mashrur
 */
public class test {
    public static void main(String[] args) {
        Set<Integer> leaf_set_temp = new HashSet<Integer>();
        leaf_set_temp.add(1);
        leaf_set_temp.add(3);
        
        leaf_set_temp.remove(1);
        
        for(Integer in: leaf_set_temp) {
            System.out.println(in);
        }
        
    }
}
