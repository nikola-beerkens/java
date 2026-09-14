/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author Nikola
 */
public enum BinOp {
    AndOp ("/\\",3),
    OrOp ("\\/",2),
    ImpliesOp ("=>",1);
    
    private final String string;
    private final int position;
    
    private BinOp(String string, int position){
           this.string = string;
           this.position = position;
       }
    
    public String getString(){
        return string;
    }
    
    public int getPosition(){
        return position;
    }
}
