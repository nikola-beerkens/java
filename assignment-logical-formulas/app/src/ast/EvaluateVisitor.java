/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.Map;

public class EvaluateVisitor implements FormulaVisitor<Boolean, Void>{
    private final Map<String,Boolean> map;
            
    public EvaluateVisitor(Map<String,Boolean> map){
        this.map=map;
    }
    
    @Override
    public Boolean visit(Not form, Void a) {
        return !form.getOperand().accept(this, a);
    }

    @Override
   public Boolean visit(BinaryOperator form, Void a){
        Boolean lres= form.getLeft().accept(this,a);
        Boolean rres=form.getRight().accept(this,a);
        switch(form.getOp()){
            case AndOp:     return lres && rres;
            case OrOp:      return lres || rres;
            case ImpliesOp:  return !lres || rres;
        }
        return null;
    }

    @Override
    public Boolean visit(Atom form, Void a) {
       return map.get(form.getId());
        
    }

    @Override
    public Boolean visit(Constant form, Void a) {
        return form.getValue();
    }
    
}