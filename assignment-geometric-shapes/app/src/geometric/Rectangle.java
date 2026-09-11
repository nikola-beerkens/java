/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometric;

public class Rectangle implements Geometric {
    final private double height;
    final private double length;
    private double x;
    private double y;
    private final int count;
    
    public Rectangle(double x, double y, double height, double length, int count){
        this.x = x; 
        this.y = y;
        this.height = height; 
        this.length = length;
        this.count = count;
    }
    
    @Override
    public double Area() {
        return ( length *  height);
    }

    @Override
    public void Move(double dx, double dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }
    
    @Override
    public String toString(){
        double xbound = leftBorder();
        double ybound = bottomBorder();
        return ("Rectangle, Length: " + length + ", Height: " + height + ", Position: (" + x + ", " + y+ "), Left bottom point: (" + xbound + ", " + ybound + ")" + " Area: " + Area());
    }

    @Override
    public double leftBorder() {
        return x; 
    }

    @Override
    public double rightBorder() {
        return (x+length); 
    }

    @Override
    public double topBorder() {
        return (y+height); 
    }

    @Override
    public double bottomBorder() {
        return y; 
    }

}