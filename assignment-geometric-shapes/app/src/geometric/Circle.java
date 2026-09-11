/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

public class Circle implements Geometric {
    private final double radius;
    private double x;
    private double y;
    private final int count;
    
    public Circle(double x, double y, double radius, int count){
        this.x = x; 
        this.y = y;
        this.radius = radius;
        this.count = count;
    }
    
    
    @Override
    public double Area() {
        return Math.PI * radius * radius;
    }

   
    public void Move(double dx, double dy) {
        this.x = this.x + dx;
        this.y = this.y + dy; 
    }
    
    @Override
    public  String  toString(){
        double xbound = leftBorder();
        double ybound = bottomBorder();
        return ("Circle, Radius: " + radius +", Center: (" + x + ", " + y + "), Left bottom point: (" + xbound + ", " + ybound + "), Area: " + Area());
    }

    @Override
    public double leftBorder() {
        return (x-radius);
    }

    @Override
    public double rightBorder() {
        return (x+radius); 
    }

    @Override
    public double topBorder() {
        return (y+radius); 
    }

    @Override
    public double bottomBorder() {
        return (y-radius); 
    }

}