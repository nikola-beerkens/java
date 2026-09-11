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
public interface Geometric extends Comparable<Geometric> {
    double Area(); 
    void Move(double x, double y); 
    double leftBorder();
    double rightBorder();
    double topBorder();
    double bottomBorder();
    

    @Override
    default int compareTo(Geometric o){
        Geometric geometric = (Geometric) o;
        if(this.Area() - geometric.Area() == 0){
            return 0;
        }
        if(this.Area() - geometric.Area()> 0){
            return 1; 
        }
        else{
            return -1; 
        }
    }
}
