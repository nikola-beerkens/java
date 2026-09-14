/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

public class BinaryOperator implements Formula {
    private final BinOp binOp;
    private final Formula leftOperand;
    private final Formula rightOperand;
    
    public BinaryOperator(BinOp op,Formula left, Formula right){
        this.binOp=op;
        this.leftOperand=left;
        this.rightOperand=right;
    }
    
    public Formula getLeft(){
        return leftOperand;
    }
   
    public Formula getRight(){
        return rightOperand;
    }
    
    public BinOp getOp(){
        return binOp;
    }
    
    
     @Override
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a) {
        return visitor.visit(this, a);
    }

  
}
