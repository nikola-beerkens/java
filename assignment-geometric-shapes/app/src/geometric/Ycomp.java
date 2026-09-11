/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

/**
 *
 * @author Nikola
 */
import java.util.Comparator;


public class Ycomp implements Comparator<Geometric>{
   
    @Override
    public int compare(Geometric g1, Geometric g2){
        return Double.compare(g1.bottomBorder(), g2.bottomBorder());
    }
}