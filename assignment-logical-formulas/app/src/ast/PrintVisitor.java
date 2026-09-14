/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

public class PrintVisitor implements FormulaVisitor<Void,Integer>{
    private final StringBuilder result;
    
    public PrintVisitor(){
        result = new StringBuilder();
    }
    
    public String getResult() {
        return result.toString();
    } 
    
     @Override
    public Void visit(Atom form, Integer i) {
        result.append(form.getId());
        return null;
    }

    @Override
    public Void visit(Not form, Integer i) {
       result.append("!");
       form.getOperand().accept(this,4);
       return null;
    }

    @Override
    public Void visit(Constant form, Integer i) {
        result.append(form == Constant.True ? "True" : "False");
        return null;
    }
    
    @Override
    public Void visit(BinaryOperator form, Integer i) {
        if (i>=form.getOp().getPosition()){
            result.append("(");
            form.getLeft().accept(this, form.getOp().getPosition());
            result.append(form.getOp().getString());
            form.getRight().accept(this, form.getOp().getPosition());
            result.append(")");
        }
        
        else{
            form.getLeft().accept(this, form.getOp().getPosition());
            result.append(form.getOp().getString());
            form.getRight().accept(this, form.getOp().getPosition());
        }
        return null;
    }

   
    
}
