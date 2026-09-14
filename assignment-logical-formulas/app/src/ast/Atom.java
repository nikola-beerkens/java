/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;
     
public class Atom implements Formula {
    
    private final String id;
    public Atom(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    
     @Override
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a) {
        return visitor.visit(this, a);  
    }
}
