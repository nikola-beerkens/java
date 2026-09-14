/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

public enum Constant implements Formula{
    True(true), False(false);
    
    private final boolean value;
    
    Constant( Boolean val ) {
        this.value = val;
    }
    
     public Boolean getValue() {
        return value;
    }

    @Override
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a) {
        return visitor.visit(this, a);
    }
}